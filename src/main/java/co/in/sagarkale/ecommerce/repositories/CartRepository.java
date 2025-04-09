package co.in.sagarkale.ecommerce.repositories;

import co.in.sagarkale.ecommerce.entities.Cart;
import co.in.sagarkale.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserIdAndIsActiveTrue(Long userId);
    Cart findByIdAndIsActiveTrue(int cartId);

    @Modifying
    @Transactional
    @Query("UPDATE Cart c SET c.isActive = false WHERE c.user.id = :userId AND c.isActive = true")
    void softDeleteByUserIdAndIsActiveTrue(@Param("userId") Long userId);
}
