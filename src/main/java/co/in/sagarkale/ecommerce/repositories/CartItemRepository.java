package co.in.sagarkale.ecommerce.repositories;

import co.in.sagarkale.ecommerce.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}
