package lk.ijse.car_rental.dto;

import lk.ijse.car_rental.entity.Rent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PaymentDTO {
    private Integer paymentId;
    private RentDTO rentId;
    private String type;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private BigDecimal total;
    private BigDecimal cash;
    private BigDecimal balance;
}
