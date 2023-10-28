package lk.ijse.car_rental.repo;

import lk.ijse.car_rental.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentRepo extends JpaRepository<Rent,String> {
    @Query(value = "select rentId from Rent order by rentId  desc limit 1", nativeQuery = true)
    String getLastRentId() throws RuntimeException;

    @Query(value = "select count (rentId) from Rent where status!='Closed'", nativeQuery = true)
    int countBookings() throws RuntimeException;

    List<Rent> getRentsByNic_Nic(String nic) throws RuntimeException;

}
