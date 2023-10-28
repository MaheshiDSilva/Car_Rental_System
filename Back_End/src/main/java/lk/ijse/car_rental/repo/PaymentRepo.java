package lk.ijse.car_rental.repo;

import lk.ijse.car_rental.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment,Integer> {

    List<Payment>findAllByRentId_Nic(String nic) throws RuntimeException;

    @Query(value = "select sum (total) from Payment where month (`date`)=month (date (now()))", nativeQuery = true)
    BigDecimal getCurrentMonthIncome() throws RuntimeException;

    @Query(value = "select sum (total) from Payment where `date`= date (now()) ", nativeQuery = true)
    BigDecimal getCurrentDayIncome() throws RuntimeException;

    @Query(value = "select sum(total) from Payment where year (`date`)=year (date (now())) ", nativeQuery = true)
    BigDecimal getCurrentYearIncome() throws RuntimeException;

    @Query(value = "select `date`, sum (total) from Payment group by `date`",nativeQuery = true)
    List getDailyIncome() throws RuntimeException;

    @Query(value = "select month (`date`), sum (total) from Payment group by month(`date`)", nativeQuery = true)
    List getMonthlyIncome() throws RuntimeException;

    @Query(value = "select year (`date`), sum (total) from Payment group by year(`date`)", nativeQuery = true)
    List getYearlyIncome() throws RuntimeException;


}
