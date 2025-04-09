package co.in.sagarkale.ecommerce.services;

import co.in.sagarkale.ecommerce.entities.*;
import co.in.sagarkale.ecommerce.repositories.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final JwtService jwtService;

    public List<Order> getAllUserOrders(HttpServletRequest request){
        long userId = extractUserIdFromToken(request);
        if (userId == 0) {
            return null;
        }
        return orderRepository.findByUserIdAndIsActiveTrue(userId);
    }

    public Order createOrder(HttpServletRequest request, int cartId) {
        long userId = extractUserIdFromToken(request);
        Cart existingCart = cartRepository.findByUserIdAndIsActiveTrue(userId);

        if (userId == 0 || existingCart == null || existingCart.getCartItem().size() == 0) {
            return null;
        }

        Order order = new Order();
        order.setDate(LocalDateTime.now());
        order.setActive(true);
        order.setStatus("Placed");
        order.setUser(userRepository.findById((int)userId).get());

        List<OrderItem> orderItems = new ArrayList<>();
        float orderAmount = 0;
        Order createdOrder = orderRepository.save(order);

        for (CartItem cartItem: existingCart.getCartItem()){
            OrderItem orderItem = new OrderItem();
            orderItem.setActive(true);
            orderItem.setOrder(createdOrder);
            Product product = productRepository.findById(cartItem.getProductId()).get();
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);

            orderAmount += product.getPrice();
        }

        orderItemRepository.saveAll(orderItems);
        order.setAmount(orderAmount);
        createdOrder.setOrderItem(orderItems);

        return createdOrder;
    }

    private long extractUserIdFromToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token =  token.substring(7); // Extract the token without "Bearer "
        }else {
            return 0L;
        }
        return jwtService.extractUserId(token);
    }
}
