package lk.ijse.car_rental.controller;

import lk.ijse.car_rental.service.UserService;
import lk.ijse.car_rental.util.CurrentUserUtil;
import lk.ijse.car_rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/login")
@CrossOrigin
@Transactional

public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseUtil getUser(@RequestParam String username, @RequestParam String password) {
        return new ResponseUtil("OK", "Successfully Loaded..!", userService.getUser(username, password));
    }

    @GetMapping
    public ResponseUtil getCurrentUserDetails() {
        System.out.println(CurrentUserUtil.currentUser);
        return new ResponseUtil("OK", "Successfully Loaded..!", CurrentUserUtil.currentUser);

    }
}
