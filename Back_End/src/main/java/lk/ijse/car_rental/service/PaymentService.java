package lk.ijse.car_rental.service;

import lk.ijse.car_rental.dto.PaymentDTO;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    void savePayment(PaymentDTO paymentDTO) throws RuntimeException;

    List<PaymentDTO> loadAllPayments() throws RuntimeException;

    List<PaymentDTO> getPaymentsByNic(String nic) throws RuntimeException;

    BigDecimal getCurrentDayIncome() throws RuntimeException;

    BigDecimal getCurrentMonthIncome() throws RuntimeException;

    BigDecimal getCurrentYearIncome() throws RuntimeException;

    List getDailyIncome() throws RuntimeException;

    List getMonthlyIncome() throws RuntimeException;

    List getYearlyIncome() throws RuntimeException;


}
