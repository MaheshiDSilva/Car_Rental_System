package lk.ijse.car_rental.util;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

public class ImageUtil {
    public static String projectPath="/H:/GDSE64 zone/Car_Rental_System/Front_End/assets";

    public String writeImage(MultipartFile file, Path location) throws Throwable{
        Files.write(location, file.getBytes());
        file.transferTo(location);

        return location.toString().replace("/H:/GDSE64 zone/Car_Rental_System/Front_End/assets","");
    }
}
