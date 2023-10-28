package lk.ijse.car_rental.repo;

import lk.ijse.car_rental.entity.RentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentDetailsRepo extends JpaRepository<RentDetails,String> {
    List<RentDetails>getRentDetailsByNic(String nic) throws RuntimeException;

    void deleteRentDetailsByRegNum(String rentId) throws RuntimeException;
}
