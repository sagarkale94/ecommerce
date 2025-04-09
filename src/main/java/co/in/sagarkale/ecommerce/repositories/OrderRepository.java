package co.in.sagarkale.ecommerce.repositories;

import co.in.sagarkale.ecommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserIdAndIsActiveTrue(long userId);
}
