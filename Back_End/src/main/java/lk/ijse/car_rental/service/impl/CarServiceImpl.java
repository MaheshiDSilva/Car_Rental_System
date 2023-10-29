package lk.ijse.car_rental.service.impl;

import lk.ijse.car_rental.dto.CarDTO;
import lk.ijse.car_rental.dto.CarPhotoDTO;
import lk.ijse.car_rental.dto.CarSpDTO;
import lk.ijse.car_rental.entity.Car;
import lk.ijse.car_rental.repo.CarRepo;
import lk.ijse.car_rental.repo.RentDetailsRepo;
import lk.ijse.car_rental.service.CarService;
import lk.ijse.car_rental.util.ImageUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CarRepo carRepo;

    @Autowired
    RentDetailsRepo rentDetailsRepo;


    @Override
    public void saveCar(CarDTO carDTO) throws RuntimeException {
        if (carRepo.existsById(carDTO.getRegNum())) throw new RuntimeException("Car Already Exist..!");

        Car car = modelMapper.map(carDTO, Car.class);
        try {

            car.getCarPhoto().setFrontPhoto(new ImageUtil().writeImage(carDTO.getCarPhoto().getFrontPhoto(), Paths.get(ImageUtil.projectPath + "/img/photo/car/front_" + carDTO.getRegNum() + ".jpg")));
            car.getCarPhoto().setBackPhoto(new ImageUtil().writeImage(carDTO.getCarPhoto().getBackPhoto(), Paths.get(ImageUtil.projectPath + "/img/photo/car/back_" + carDTO.getRegNum() + ".jpg")));
            car.getCarPhoto().setSidePhoto(new ImageUtil().writeImage(carDTO.getCarPhoto().getSidePhoto(), Paths.get(ImageUtil.projectPath + "/img/photo/car/side_" + carDTO.getRegNum() + ".jpg")));
            car.getCarPhoto().setInteriorPhoto(new ImageUtil().writeImage(carDTO.getCarPhoto().getInteriorPhoto(), Paths.get(ImageUtil.projectPath + "/img/photo/car/interior_" + carDTO.getRegNum() + ".jpg")));

            carRepo.save(car);

        } catch (Throwable e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public void updateCar(CarDTO carDTO) throws RuntimeException {
        if (!carRepo.existsById(carDTO.getRegNum())) throw new RuntimeException("Car Doesn't Exist..!");
        Car car1 = carRepo.findById(carDTO.getRegNum()).get();
        Car car = modelMapper.map(carDTO, Car.class);
        car.setCarPhoto(car1.getCarPhoto());
        carRepo.save(car);
    }

    @Override
    public void deleteCar(String regNum) throws RuntimeException {
        if (!carRepo.existsById(regNum)) throw new RuntimeException("Car Doesn't Exist..!");
        rentDetailsRepo.deleteRentDetailsByRegNum(regNum);
        carRepo.deleteById(regNum);
    }

    @Override
    public List<CarDTO> getAllCars() throws RuntimeException {
        return modelMapper.map(carRepo.findAll(), new TypeToken<ArrayList<CarSpDTO>>() {
        }.getType());
    }

    @Override
    public void saveCarImages(CarPhotoDTO carPhotoDTO) throws RuntimeException {

    }

    @Override
    public void addToMaintains(String regNum) throws RuntimeException {
        Car car = carRepo.findCarByRegNum(regNum);
        car.setAvailability(car.getAvailability().equals("YES") ? "NO" : "YES");
        carRepo.save(car);
    }

    @Override
    public CarSpDTO getCar(String regNum) throws RuntimeException {
        return modelMapper.map(carRepo.findById(regNum),CarSpDTO.class);
    }

    @Override
    public int countAvailableCars() throws RuntimeException {
        return carRepo.countAvailableCars();
    }

    @Override
    public int countReservedCars() throws RuntimeException {
        return carRepo.countReservedCars();
    }

    @Override
    public int countMaintainingCars() throws RuntimeException {
        return carRepo.countMaintainingCars();
    }

    @Override
    public List<CarSpDTO> filterCarsByRegNum(String text, String search, String fuel) throws RuntimeException {
        fuel = fuel.equals("ALL") ? "" : fuel;

        switch (search) {
            case "REG_NUM":
                return modelMapper.map(carRepo.findByRegNumAndFuelType("%" + text + "%", "%"+fuel+"%"), new TypeToken<ArrayList<CarSpDTO>>() {
                }.getType());

            case "BRAND":
                return modelMapper.map(carRepo.findByBrandAndFuelType("%" + text + "%", "%"+fuel+"%"), new TypeToken<ArrayList<CarSpDTO>>() {
                }.getType());

            case "COLOR":
                return modelMapper.map(carRepo.findByColorAndFuelType("%" + text + "%", "%"+fuel+"%"), new TypeToken<ArrayList<CarSpDTO>>() {
                }.getType());

            default:
                return null;

        }
    }

    @Override
    public List countCarsByBrand() throws RuntimeException {
        return carRepo.countCarBrands();
    }
}
