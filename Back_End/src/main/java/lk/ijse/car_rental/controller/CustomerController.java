package lk.ijse.car_rental.controller;

import lk.ijse.car_rental.dto.CustomerDTO;
import lk.ijse.car_rental.dto.CustomerPhotoDTO;
import lk.ijse.car_rental.service.CustomerService;
import lk.ijse.car_rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/customer")
@CrossOrigin
@Transactional
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseUtil saveCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.saveCustomer(customerDTO);
        return new ResponseUtil("OK", "Successfully Saved..!", "");
    }

    @PutMapping
    public ResponseUtil updateCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(customerDTO);
        return new ResponseUtil("OK","Successfully Updated..!","");
    }

    @DeleteMapping
    public ResponseUtil deleteCustomer(@RequestParam String nic){
        customerService.deleteCustomer(nic);
        return new ResponseUtil("OK","Successfully Deleted..!","");
    }

    @GetMapping
    public ResponseUtil getAll(){
        return new ResponseUtil("OK","Successfully Loaded..!",customerService.getAllCustomer());
    }

    @GetMapping(path = "/count")
    public ResponseUtil countCustomers(){
        return new ResponseUtil("OK","Successfully Loaded..!",customerService.countCustomers());
    }

    @PostMapping(params = {"image"})
    public ResponseUtil saveImages(@ModelAttribute CustomerPhotoDTO customerPhotoDTO){
        customerService.saveImages(customerPhotoDTO.getNic(),customerPhotoDTO);
        return new ResponseUtil("Ok","Successfully Saved..!","");
    }


}
