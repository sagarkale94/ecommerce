package co.in.sagarkale.ecommerce.controllers;

import co.in.sagarkale.ecommerce.dtos.ApiResp;
import co.in.sagarkale.ecommerce.dtos.AuthenticationRequestDTO;
import co.in.sagarkale.ecommerce.entities.Cart;
import co.in.sagarkale.ecommerce.entities.CartItem;
import co.in.sagarkale.ecommerce.entities.Product;
import co.in.sagarkale.ecommerce.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@Tag(name = "Cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @Operation(
            description = "Get endpoint for cart details",
            summary = "Get cart details of user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content =  @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = Cart.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResp.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<ApiResp<Cart>> get(HttpServletRequest request) {
        Cart userCart = cartService.getUserCart(request);
        if(userCart != null){
            ApiResp<Cart> apiResponse = new ApiResp<>(
                    true,
                    "",
                    cartService.getUserCart(request)
            );
            return ResponseEntity.ok(apiResponse);
        }else{
            ApiResp<Cart> apiResponse = new ApiResp<>(
                    false,
                    "",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @Operation(
            description = "POST endpoint for cart",
            summary = "Create new cart",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "201",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Cart.class))
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResp.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ApiResp<Cart>> post(HttpServletRequest request) {
        Cart cartCreated = cartService.createCart(request);
        if (cartCreated != null) {
            ApiResp<Cart> apiResponse = new ApiResp<>(true, "Cart created Successfully", cartCreated);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } else {
            ApiResp<Cart> apiResponse = new ApiResp<>(false, "Failed to create cart", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @Operation(
            description = "DELETE endpoint for cart",
            summary = "Delete user cart",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Cart.class))
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResp.class))
                    )
            }
    )
    @DeleteMapping
    public ResponseEntity<ApiResp<Cart>> delete(HttpServletRequest request) {
        Cart cartDeleted = cartService.deleteUserCart(request);
        if (cartDeleted != null) {
            ApiResp<Cart> apiResponse = new ApiResp<>(true, "Cart Deleted Successfully", cartDeleted);
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResp<Cart> apiResponse = new ApiResp<>(false, "No active cart for user", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @Operation(
            description = "POST endpoint for add items to cart",
            summary = "Add items to cart",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "201",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Cart.class))
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResp.class))
                    )
            }
    )
    @PostMapping("/add-items/{cartId}")
    public ResponseEntity<ApiResp<Cart>> addToCart(HttpServletRequest request, @RequestBody List<CartItem> cartItem, @RequestParam int cartId) {
        Cart updatedCart = cartService.addItemsToCart(request, cartItem, cartId);
        if (updatedCart != null) {
            ApiResp<Cart> apiResponse = new ApiResp<>(true, "Items added to cart successfully", null);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } else {
            ApiResp<Cart> apiResponse = new ApiResp<>(false, "Failed to add items to cart", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

}
