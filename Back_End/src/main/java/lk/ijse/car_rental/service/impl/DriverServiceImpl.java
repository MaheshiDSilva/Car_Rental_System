package lk.ijse.car_rental.service.impl;

import lk.ijse.car_rental.dto.DriverDTO;
import lk.ijse.car_rental.dto.DriverSpDTO;
import lk.ijse.car_rental.entity.Driver;
import lk.ijse.car_rental.repo.DriverRepo;
import lk.ijse.car_rental.service.DriverService;
import lk.ijse.car_rental.util.CurrentUserUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void saveDriver(DriverDTO driverDTO) throws RuntimeException {
        Driver driver = modelMapper.map(driverDTO, Driver.class);
        if(driverRepo.existsById(driverDTO.getNic())) throw new RuntimeException("Driver already exists..!");
        try {
            if (driverDTO.getLicenseImage().getBytes() != null) {

                byte[] licenseFileBytes = driverDTO.getLicenseImage().getBytes();

                String projectPath = "/H:/GDSE64 zone/Car_Rental_System/Front_End/assets";
                Path licenseLocation = Paths.get(projectPath + "/img/photo/driver/license_" + driver.getNic() + ".jpg");

                Files.write(licenseLocation, licenseFileBytes);

                driverDTO.getLicenseImage().transferTo(licenseLocation);

                driver.setLicenseImage("/img/photo/driver/license_" + driver.getNic() + ".jpg");

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.setAvailabilityStatus("YES");
        driver.getUser().setRole("Driver");
        driverRepo.save(driver);
    }

    @Override
    public void updateDriver(DriverDTO driverDTO) throws RuntimeException {
        Driver driver = modelMapper.map(driverDTO, Driver.class);
        if(!driverRepo.existsById(driverDTO.getNic())) throw new RuntimeException("Invalid Driver..!");
        Driver driver1 = driverRepo.findById(driverDTO.getNic()).get();
        driver.setLicenseImage(driver1.getLicenseImage());

        driver.setAvailabilityStatus("YES");
        driver.getUser().setRole("Driver");
        driverRepo.save(driver);
    }

    @Override
    public void deleteDriver(String nic) throws RuntimeException {
        if (!driverRepo.existsById(nic)) throw new RuntimeException("Invalid Driver..!");
        driverRepo.deleteById(nic);
    }

    @Override
    public List<DriverDTO> getAllDrivers() throws RuntimeException {
        return modelMapper.map(driverRepo.findAll(), new TypeToken<ArrayList<DriverSpDTO>>() {
        }.getType());
    }

    @Override
    public DriverDTO getDriver() throws RuntimeException {
        return modelMapper.map(driverRepo.getDriversByUsername(CurrentUserUtil.currentUser.getUsername()), DriverDTO.class);
    }

    @Override
    public int countAvailableDrivers() throws RuntimeException {
        return driverRepo.countAvailableDrivers();

    }

    @Override
    public int countReservedDrivers() throws RuntimeException {
        return driverRepo.countReservedDrivers();
    }
}
