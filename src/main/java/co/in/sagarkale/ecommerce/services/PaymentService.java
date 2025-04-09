package co.in.sagarkale.ecommerce.services;

import co.in.sagarkale.ecommerce.entities.Cart;
import co.in.sagarkale.ecommerce.entities.Order;
import co.in.sagarkale.ecommerce.entities.Product;
import co.in.sagarkale.ecommerce.repositories.CartRepository;
import co.in.sagarkale.ecommerce.repositories.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final CartRepository cartRepository;
    private final OrderService orderService;

    public Order pay(HttpServletRequest request, int cartId){
        Cart cart = cartRepository.findByIdAndIsActiveTrue(cartId);
        if(cart != null){
            //TODO: Integration with actual payment gateway
            
            return orderService.createOrder(request, cartId);
        }
        return null;
    }
}
