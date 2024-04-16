package tr.com.workintech.s18d4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.workintech.s18d4.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
