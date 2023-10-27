package lk.ijse.car_rental.dto;

import lk.ijse.car_rental.embedded.CarPhoto;
import lk.ijse.car_rental.embedded.FreeMileage;
import lk.ijse.car_rental.embedded.PriceRate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CarSpDTO {
    private String regNum;
    private String color;
    private String type;
    private String brand;
    private String availability;
    private String transmissionType;
    private String fuelType;
    private int passengers;
    private BigDecimal extraKMPrice;
    private BigDecimal lostDamageCost;
    private String meterValue;
    private PriceRate priceRate;
    private FreeMileage freeMileage;
    private CarPhoto carPhoto;
}
