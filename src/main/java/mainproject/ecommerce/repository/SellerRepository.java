package mainproject.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mainproject.ecommerce.dto.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer>{

	boolean existsByEmail(String email);

	boolean existsByMobile(long mobile);

	Seller findByEmail(String email);
	
	

}
