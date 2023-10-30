package lk.ijse.car_rental.controller;

import lk.ijse.car_rental.dto.DriverDTO;
import lk.ijse.car_rental.dto.UserDTO;
import lk.ijse.car_rental.service.DriverService;
import lk.ijse.car_rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/driver")
@CrossOrigin
@Transactional
public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping
    public ResponseUtil saveDriver(@RequestParam String username, @RequestParam String password, @ModelAttribute DriverDTO driverDTO) {
        driverDTO.setUser(new UserDTO(username, password, "Driver"));
        driverService.saveDriver(driverDTO);
        return new ResponseUtil("OK", "Successfully Saved..!", "");
    }

    @PostMapping(path = "/update")
    public ResponseUtil updateDriver(@RequestParam String username, @RequestParam String password, @ModelAttribute DriverDTO driverDTO) {
        driverDTO.setUser(new UserDTO(username, password, "Driver"));
        driverService.updateDriver(driverDTO);
        return new ResponseUtil("OK", "Successfully Updated..!", "");
    }

    @DeleteMapping
    public ResponseUtil deleteDriver(String nic) {
        driverService.deleteDriver(nic);
        return new ResponseUtil("OK", "Successfully Deleted..!", "");
    }

    @GetMapping(path = "/all")
    public ResponseUtil getAll() {
        return new ResponseUtil("OK", "Successfully Loaded..!", driverService.getAllDrivers());
    }

    @GetMapping(path = "/current")
    public ResponseUtil getCurrentDriver() {
        return new ResponseUtil("OK", "Successfully Loaded..!", driverService.getDriver());
    }

    public ResponseUtil getDriverSchedule(String nic) {
        return null;
    }

    @GetMapping(path = "/available")
    public ResponseUtil availableDrivers() {
        return new ResponseUtil("OK", "Successfully Loaded..!", driverService.countAvailableDrivers());

    }

    @GetMapping(path = "/reserved")
    public ResponseUtil reservedDrivers() {
        return new ResponseUtil("OK", "Successfully Loaded..!", driverService.countReservedDrivers());
    }


}
