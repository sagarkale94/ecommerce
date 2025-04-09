package co.in.sagarkale.ecommerce.repositories;

import co.in.sagarkale.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
