package lk.ijse.car_rental.service.impl;

import lk.ijse.car_rental.dto.CustomerDTO;
import lk.ijse.car_rental.dto.CustomerPhotoDTO;
import lk.ijse.car_rental.entity.Customer;
import lk.ijse.car_rental.repo.CustomerRepo;
import lk.ijse.car_rental.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) throws RuntimeException {
        if (customerRepo.existsById(customerDTO.getNic())) throw new RuntimeException("Customer Already Exits..!");
        customerDTO.getUser().setRole("Customer");
        customerRepo.save(modelMapper.map(customerDTO, Customer.class));
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) throws RuntimeException {
        if (!customerRepo.existsById(customerDTO.getNic())) throw new RuntimeException("Invalid Customer..!");
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer customer1 = customerRepo.findById(customerDTO.getNic()).get();
        customer.setLicenseImage(customer1.getLicenseImage());
        customer.setNicImage(customer1.getNicImage());
        customer.getUser().setRole("Customer");
        customerRepo.save(customer);
    }

    @Override
    public void deleteCustomer(String nic) throws RuntimeException {
        if (!customerRepo.existsById(nic)) throw new RuntimeException("Invalid Customer..!");
        customerRepo.deleteById(nic);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws RuntimeException {
        return modelMapper.map(customerRepo.findAll(), new TypeToken<ArrayList<CustomerDTO>>() {
        }.getType());
    }

    @Override
    public void saveImages(String nic, CustomerPhotoDTO imageDTO) throws RuntimeException {
        Customer customer = customerRepo.findById(nic).get();

        try {
            if (imageDTO.getLicenseImage() != null && imageDTO.getNicImage() != null) {

                byte[] nicFileBytes = imageDTO.getNicImage().getBytes();
                byte[] licenseFileBytes = imageDTO.getLicenseImage().getBytes();

                String projectPath = "/H:/GDSE64 zone/Car_Rental_System/Front_End/assets";
                Path nicLocation = Paths.get(projectPath + "/img/photo/customer/nic/nic_" + nic + ".jpg");
                Path licenseLocation = Paths.get(projectPath + "/img/photo/customer/license/license_" + nic + ".jpg");

                Files.write(nicLocation, nicFileBytes);
                Files.write(licenseLocation, licenseFileBytes);

                imageDTO.getNicImage().transferTo(nicLocation);
                imageDTO.getLicenseImage().transferTo(licenseLocation);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        customer.setNicImage("/assets/img/photo/customer/nic/nic_" + nic + ".jpg");
        customer.setLicenseImage("/assets/img/photo/customer/license/license_" + nic + ".jpg");

        customer.getUser().setRole("Customer");
        customerRepo.save(customer);
    }

    @Override
    public int countCustomers() throws RuntimeException {
        return customerRepo.countCustomerByNic();
    }
}
