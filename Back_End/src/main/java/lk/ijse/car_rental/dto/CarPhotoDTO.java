package lk.ijse.car_rental.dto;

import org.springframework.web.multipart.MultipartFile;

public class CarPhotoDTO {
    private MultipartFile frontPhoto;
    private MultipartFile backPhoto;
    private MultipartFile sidePhoto;
    private MultipartFile interiorPhoto;
}
