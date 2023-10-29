package lk.ijse.car_rental.repo;

import lk.ijse.car_rental.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepo extends JpaRepository<Car,String> {

    Car findCarByRegNum(String regNum) throws RuntimeException;

    @Query(value = "select count(regNum) from Car where Availability='YES'", nativeQuery = true)
    int countReservedCars()throws RuntimeException;

    @Query(value = "select count(regNum) from Car where Availability='NO'", nativeQuery = true)
    int countAvailableCars() throws RuntimeException;

    List<Car> findByRegNumAndFuelType(String regNum,String fuelType) throws RuntimeException;

    List<Car> findByBrandAndFuelType(String brand,String fuelType) throws RuntimeException;

    List<Car> findByColorAndFuelType(String color,String fuelType) throws RuntimeException;

    @Query(value = "select brand, count(brand) from Car group by brand",nativeQuery = true)
    List countCarBrands() throws RuntimeException;

    @Query(value = "select count(regNum) from Car where Availability='MAINTAIN'", nativeQuery = true)
    int countMaintainingCars() throws RuntimeException;
}
