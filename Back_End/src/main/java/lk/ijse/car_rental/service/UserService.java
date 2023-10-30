package lk.ijse.car_rental.service;

import lk.ijse.car_rental.dto.UserDTO;

public interface UserService {
    UserDTO getUser(String username, String password) throws RuntimeException;
}
