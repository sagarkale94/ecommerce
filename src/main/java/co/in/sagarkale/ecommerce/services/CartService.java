package co.in.sagarkale.ecommerce.services;

import co.in.sagarkale.ecommerce.entities.Cart;
import co.in.sagarkale.ecommerce.entities.CartItem;
import co.in.sagarkale.ecommerce.repositories.CartItemRepository;
import co.in.sagarkale.ecommerce.repositories.CartRepository;
import co.in.sagarkale.ecommerce.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final JwtService jwtService;

    public Cart getUserCart(HttpServletRequest request){
        long userId = extractUserIdFromToken(request);
        if (userId == 0) {
            return null;
        }
        return cartRepository.findByUserIdAndIsActiveTrue(userId);
    }

    public Cart createCart(HttpServletRequest request) {

        long userId = extractUserIdFromToken(request);
        Cart existingCart = cartRepository.findByUserIdAndIsActiveTrue(userId);

        if (userId == 0 || existingCart != null) {
            return null;
        }

        // Create new cart for user if no active cart exists
        Cart newCart = new Cart();
        newCart.setUser(userRepository.findById(Math.toIntExact(userId)).get());
        newCart.setActive(true);

        return cartRepository.save(newCart);
    }

    public Cart deleteUserCart(HttpServletRequest request){
        long userId = extractUserIdFromToken(request);
        if (userId == 0) {
            return null;
        }

        Cart cart = cartRepository.findByUserIdAndIsActiveTrue(userId);

        if (cart != null) {
            cartRepository.softDeleteByUserIdAndIsActiveTrue(userId);
            cart.setActive(false);
            return cart;
        } else {
            return null;
        }
    }

    public Cart addItemsToCart(HttpServletRequest request, List<CartItem> cartItems, int cartId) {
        Cart userCart = getUserCart(request);

        //Check if cart belongs to same user
        if(userCart.getId() != cartId && cartItems.size() > 0){
           return null;
        }

        // Set the cart reference for each cart item
        for (CartItem cartItem : cartItems) {
            cartItem.setCart(userCart);  // Associate the cart with each cart item
            cartItem.setActive(true);
        }

        //TODO: To add check
        // 1. Product is active
        // 2. Quantity is > 0
        // 3. Cart is active

        // Save the cart items
        cartItemRepository.saveAll(cartItems);

        // Return the updated cart
        userCart.getCartItem().addAll(cartItems);  // Optionally add to the cart's cartItem list
        return userCart;

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
