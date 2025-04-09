package co.in.sagarkale.ecommerce.repositories;

import co.in.sagarkale.ecommerce.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
