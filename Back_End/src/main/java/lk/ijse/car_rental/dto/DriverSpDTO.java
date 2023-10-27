package lk.ijse.car_rental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DriverSpDTO {
    private String nic;
    private String name;
    private String address;
    private String contact;
    private String email;
    private String license;
    private String availabilityStatus;
    private String licenseImage;
    private UserDTO user;
}
