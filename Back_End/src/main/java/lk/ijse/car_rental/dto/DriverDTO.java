package lk.ijse.car_rental.dto;

import lk.ijse.car_rental.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DriverDTO {
    private String nic;
    private String name;
    private String address;
    private String contact;
    private String email;
    private String license;
    private String availabilityStatus;
    private MultipartFile licenseImage;
    private UserDTO user;
}
