package lk.ijse.car_rental.entity;

import lk.ijse.car_rental.embedded.CarPhoto;
import lk.ijse.car_rental.embedded.FreeMileage;
import lk.ijse.car_rental.embedded.PriceRate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Car {
    @Id
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
    @Embedded
    private PriceRate priceRate;
    @Embedded
    private FreeMileage freeMileage;
    @Embedded
    private CarPhoto carPhoto;



}
