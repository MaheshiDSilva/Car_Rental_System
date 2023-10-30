package lk.ijse.car_rental.service.impl;

import lk.ijse.car_rental.dto.UserDTO;
import lk.ijse.car_rental.entity.User;
import lk.ijse.car_rental.repo.UserRepo;
import lk.ijse.car_rental.service.UserService;
import lk.ijse.car_rental.util.CurrentUserUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDTO getUser(String username, String password) throws RuntimeException {
        User user = userRepo.findById(username).get();
        if (!user.getPassword().equals(password)) throw new RuntimeException("Wrong Login Details. Please Try Again..!");

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        CurrentUserUtil.currentUser = userDTO;
        return userDTO;
    }
}
