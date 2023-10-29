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
public class CarPhotoDTO {
    private MultipartFile frontPhoto;
    private MultipartFile backPhoto;
    private MultipartFile sidePhoto;
    private MultipartFile interiorPhoto;
}
