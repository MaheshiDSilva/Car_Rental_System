package lk.ijse.car_rental.service;

import lk.ijse.car_rental.dto.DriverDTO;

import java.util.List;

public interface DriverService {
    void saveDriver(DriverDTO driverDTO) throws RuntimeException;

    void updateDriver(DriverDTO driverDTO) throws RuntimeException;

    void deleteDriver(String nic) throws RuntimeException;

    List<DriverDTO> getAllDrivers() throws RuntimeException;

    DriverDTO getDriver() throws RuntimeException;

    int countAvailableDrivers() throws RuntimeException;

    int countReservedDrivers() throws RuntimeException;
}
