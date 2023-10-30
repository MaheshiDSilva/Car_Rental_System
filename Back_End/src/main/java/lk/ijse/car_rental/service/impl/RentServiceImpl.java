package lk.ijse.car_rental.service.impl;

import lk.ijse.car_rental.dto.CustomerDTO;
import lk.ijse.car_rental.dto.RentDTO;
import lk.ijse.car_rental.dto.RentDetailsDTO;
import lk.ijse.car_rental.entity.Car;
import lk.ijse.car_rental.entity.Driver;
import lk.ijse.car_rental.entity.Rent;
import lk.ijse.car_rental.entity.RentDetails;
import lk.ijse.car_rental.repo.*;
import lk.ijse.car_rental.service.RentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class RentServiceImpl implements RentService {

    @Autowired
    RentRepo rentRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    RentDetailsRepo rentDetailsRepo;

    @Autowired
    CarRepo carRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void requestRent(RentDTO rentDTO) throws RuntimeException {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Rent rent = modelMapper.map(rentDTO, Rent.class);

        //System.out.println(rent);

        if (rentDTO.getDriverRequest().equals("YES")) {

            List<Driver> drivers = driverRepo.getAvailableDrivers();
            int i;

            for (RentDetails rentDetail : rent.getRentDetails()) {

                i = new Random().nextInt(drivers.size());
                rentDetail.setNic(drivers.get(i).getNic());
                Car car = carRepo.findById(rentDetail.getRegNum()).get();
                car.setAvailability("NO");

                carRepo.save(car);

                drivers.get(i).setAvailabilityStatus("NO");
                driverRepo.save(drivers.get(i));
            }
        }
        rentRepo.save(rent);
    }

    @Override
    public String generateNewRentId() throws RuntimeException {
        String lastRentId = rentRepo.getLastRentId();
        return lastRentId != null ? String.format("RID-%03d", (Integer.parseInt(lastRentId.replace("RID-", "")) + 1)) : "RID-001";
    }

    @Override
    public CustomerDTO getCustomerByUsername(String username) throws RuntimeException {
        return modelMapper.map(customerRepo.getCustomerByUsername(username), CustomerDTO.class);
    }

    @Override
    public List<RentDTO> getAllRents() throws RuntimeException {
        return modelMapper.map(rentRepo.findAll(), new TypeToken<ArrayList<RentDTO>>() {
        }.getType());
    }

    @Override
    public void acceptRentRequest(String rentId, String option) throws RuntimeException {
        Rent rent = rentRepo.findById(rentId).get();

        if (option.equals("accepted")) {
            rent.setStatus("Accepted");
            rent.setDescription("Rent Accepted on " + LocalDate.now() + " " + LocalTime.now());
        } else if (option.equals("reject")) {
            rent.setStatus("Rejected");
            for (RentDetails rentDetail : rent.getRentDetails()) {
                if (rentDetail.getDriver()!=null){
                    rentDetail.getDriver().setAvailabilityStatus("YES");
                }
            }
            rent.setDescription("Rent Rejected on " + LocalDate.now() + " " + LocalTime.now());
        } else {
            rent.setStatus("Closed");
            for (RentDetails rentDetails : rent.getRentDetails()) {
                if (rentDetails.getDriver()!=null){
                    rentDetails.getDriver().setAvailabilityStatus("YES");
                    rentDetails.getCar().setAvailability("MAINTAIN");
                }
            }
            rent.setDescription("Rent Closed on " + LocalDate.now() + " " + LocalTime.now());
        }
        rentRepo.save(rent);
    }

    @Override
    public RentDTO getRentByRentId(String rentId) throws RuntimeException {
        return modelMapper.map(rentRepo.findById(rentId), RentDTO.class);
    }

    @Override
    public List<RentDTO> getRentByNic(String nic) throws RuntimeException {
        return modelMapper.map(rentRepo.getRentsByNic_Nic(nic), new TypeToken<ArrayList<RentDTO>>() {
        }.getType());
    }

    @Override
    public int countRents() throws RuntimeException {
        return rentRepo.countBookings();
    }

    @Override
    public List<RentDetailsDTO> getDriverSchedule(String nic) throws RuntimeException {
        return modelMapper.map(rentDetailsRepo.getRentDetailsByNic(nic), new TypeToken<ArrayList<RentDetailsDTO>>() {
        }.getType());

    }
}
