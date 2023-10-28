package lk.ijse.car_rental.service;

import lk.ijse.car_rental.dto.CarDTO;
import lk.ijse.car_rental.dto.CarPhotoDTO;
import lk.ijse.car_rental.dto.CarSpDTO;

import java.util.List;

public interface CarService {
    void saveCar(CarDTO carDTO) throws RuntimeException;

    void updateCar(CarDTO carDTO) throws RuntimeException;

    void deleteCar(String regNum) throws RuntimeException;

    List<CarDTO> getAllCars() throws RuntimeException;

    void saveCarImages(CarPhotoDTO carPhotoDTO) throws RuntimeException;

    void addToMaintains(String regNum) throws RuntimeException;

    CarSpDTO getCar(String regNum) throws RuntimeException;

    int countAvailableCars() throws RuntimeException;

    int countReservedCars() throws RuntimeException;

    int countMaintainingCars() throws RuntimeException;

    List<CarSpDTO> filterCarsByRegNum(String text, String search, String fuel) throws RuntimeException;

    List countCarsByBrand() throws RuntimeException;



}
