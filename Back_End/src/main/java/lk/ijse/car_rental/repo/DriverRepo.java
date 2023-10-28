package lk.ijse.car_rental.repo;

import lk.ijse.car_rental.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverRepo extends JpaRepository<Driver,String> {
    @Query(value = "select * from Driver where username=?", nativeQuery = true)
    Driver getDriversByUsername(String username) throws RuntimeException;

    @Query(value = "select * from Driver where availabilityStatus='YES'", nativeQuery = true)
    List<Driver> getAvailableDrivers() throws RuntimeException;

    @Query(value = "select count (nic) from Driver where availabilityStatus='NO'", nativeQuery = true)
    int countReservedDrivers() throws RuntimeException;

    @Query(value = "select count (nic) from Driver where availabilityStatus='YES'", nativeQuery = true)
    int countAvailableDrivers() throws RuntimeException;
}
