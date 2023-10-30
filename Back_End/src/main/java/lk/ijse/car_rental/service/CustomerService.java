package lk.ijse.car_rental.service;

import lk.ijse.car_rental.dto.CustomerDTO;
import lk.ijse.car_rental.dto.CustomerPhotoDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO) throws RuntimeException;

    void updateCustomer(CustomerDTO customerDTO) throws RuntimeException;

    void deleteCustomer(String nic) throws RuntimeException;

    List<CustomerDTO> getAllCustomer() throws RuntimeException;

    void saveImages(String nic, CustomerPhotoDTO imageDTO) throws RuntimeException;

    int countCustomers() throws RuntimeException;
}
