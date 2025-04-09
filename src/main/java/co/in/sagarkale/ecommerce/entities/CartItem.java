package co.in.sagarkale.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue
    @Schema(description = "Cart item id", defaultValue = "0")
    @JsonIgnore
    @JsonProperty
    private int id;

    @Schema(description = "Product id", defaultValue = "0")
    private int productId;

    @Schema(description = "Product quantity", defaultValue = "0")
    private int quantity;

    @Schema(description = "Is cart item active", defaultValue = "false")
    @JsonIgnore
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    public Cart cart;
}
