package co.in.sagarkale.ecommerce.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import co.in.sagarkale.ecommerce.dtos.ApiResp;
import co.in.sagarkale.ecommerce.entities.Product;
import co.in.sagarkale.ecommerce.services.ProductService;

@RestController
@RequestMapping("/api/v1/product")
@Tag(name = "Product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(
            description = "Get endpoint for products",
            summary = "Get all products",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Product.class)
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
    public ResponseEntity<ApiResp<List<Product>>> get() {
        ApiResp<List<Product>> apiResponse = new ApiResp<>(
                true,
                "",
                productService.getAllProducts()
        );
        return ResponseEntity.ok(apiResponse);
    }

    @Operation(
            description = "POST endpoint for products",
            summary = "Add new product",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "201",
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
    @PostMapping
    public ResponseEntity<ApiResp<Product>> post(@Valid @RequestBody Product product) {
        Product productAdded = productService.addProduct(product);
        if (productAdded != null) {
            ApiResp<Product> apiResponse = new ApiResp<>(true, "Product Added Successfully", productAdded);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } else {
            ApiResp<Product> apiResponse = new ApiResp<>(false, "Failed to add product", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @Operation(
            description = "PUT endpoint for products",
            summary = "Update product",
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
                    ),
                    @ApiResponse(
                            description = "Product Not Found",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResp.class))
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ApiResp<Product>> put(@PathVariable int id, @Valid @RequestBody Product product) {
        Product productUpdated = productService.updateProduct(id, product);
        if (productUpdated != null) {
            ApiResp<Product> apiResponse = new ApiResp<>(true, "Product Updated Successfully", productUpdated);
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResp<Product> apiResponse = new ApiResp<>(false, "Product not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @Operation(
            description = "Delete endpoint for product",
            summary = "Delete product by product id",
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
                    ),
                    @ApiResponse(
                            description = "Product Not Found",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResp.class))
                    )
            }
    )
    @DeleteMapping
    public ResponseEntity<ApiResp<Product>> delete(@PathVariable int productId) {
        Product productDeleted = productService.deleteProduct(productId);
        if (productDeleted != null) {
            ApiResp<Product> apiResponse = new ApiResp<>(true, "Product Deleted Successfully", productDeleted);
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResp<Product> apiResponse = new ApiResp<>(false, "Product not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

}
