package co.in.sagarkale.ecommerce.controllers;

import co.in.sagarkale.ecommerce.dtos.ApiResp;
import co.in.sagarkale.ecommerce.entities.Order;
import co.in.sagarkale.ecommerce.entities.Product;
import co.in.sagarkale.ecommerce.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pay")
@Tag(name = "Payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(
            description = "POST endpoint for payment",
            summary = "Pay",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResp.class))
                    )
            }
    )
    @PostMapping("/{cartId}")
    public ResponseEntity<ApiResp<Order>> post(HttpServletRequest request, @Valid@RequestParam int cartId) {
        Order createdOrder = paymentService.pay(request, cartId);
        if (createdOrder != null) {
            ApiResp<Order> apiResponse = new ApiResp<>(true, "Payment Successful", createdOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } else {
            ApiResp<Order> apiResponse = new ApiResp<>(false, "Payment failed", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

}
