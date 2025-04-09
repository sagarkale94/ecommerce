package co.in.sagarkale.ecommerce.controllers;

import co.in.sagarkale.ecommerce.dtos.ApiResp;
import co.in.sagarkale.ecommerce.entities.Order;
import co.in.sagarkale.ecommerce.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@Tag(name = "Order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(
            description = "Get endpoint for orders",
            summary = "Get all orders",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Order.class)
                                    )
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
    public ResponseEntity<ApiResp<List<Order>>> get(HttpServletRequest request) {
        List<Order> userOrders = orderService.getAllUserOrders(request);
        if(userOrders != null){
            ApiResp<List<Order>> apiResponse = new ApiResp<>(
                    true,
                    "",
                    userOrders
                    );
            return ResponseEntity.ok(apiResponse);
        }else{
            ApiResp<List<Order>> apiResponse = new ApiResp<>(
                    false,
                    "Failed",
                    null
                    );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }


    }
}
