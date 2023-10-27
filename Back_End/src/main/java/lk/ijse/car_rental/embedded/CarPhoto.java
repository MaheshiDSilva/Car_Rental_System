package lk.ijse.car_rental.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CarPhoto {
    private String frontPhoto;
    private String backPhoto;
    private String sidePhoto;
    private String interiorPhoto;

}
