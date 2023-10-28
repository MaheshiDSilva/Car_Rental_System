package lk.ijse.car_rental.repo;

import lk.ijse.car_rental.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepo extends JpaRepository<Customer,String> {

    @Query(value = "select * from Customer where username=?", nativeQuery = true)
    Customer getCustomerByUsername(String username) throws RuntimeException;

    Customer getCustomerByNic(String nic) throws RuntimeException;

    @Query(value = "select count(nic) from Customer", nativeQuery = true)
    int countCustomerByNic() throws RuntimeException;
}
