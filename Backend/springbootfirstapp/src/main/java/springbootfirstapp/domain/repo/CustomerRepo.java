package springbootfirstapp.domain.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootfirstapp.domain.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	List<Customer> findByNameOrPhone(String name, String phone);
}
