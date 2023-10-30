package lk.ijse.car_rental.service;

import lk.ijse.car_rental.dto.CustomerDTO;
import lk.ijse.car_rental.dto.RentDTO;
import lk.ijse.car_rental.dto.RentDetailsDTO;

import java.util.List;

public interface RentService {
    void requestRent(RentDTO rentDTO) throws RuntimeException;

    String generateNewRentId() throws RuntimeException;

    CustomerDTO getCustomerByUsername(String username) throws RuntimeException;

    List<RentDTO> getAllRents() throws RuntimeException;

    void acceptRentRequest(String rentId, String option) throws RuntimeException;

    RentDTO getRentByRentId(String rentId) throws RuntimeException;

    List<RentDTO> getRentByNic(String nic) throws RuntimeException;

    int countRents() throws RuntimeException;

    List<RentDetailsDTO> getDriverSchedule(String nic) throws RuntimeException;


}
