package springbootfirstapp.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootfirstapp.domain.entity.Seller;

public interface SellerRepo extends JpaRepository<Seller, Integer>{

}
