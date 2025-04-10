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
public class OrderItem {
    @Id
    @GeneratedValue
    @Schema(description = "Order item id", defaultValue = "0")
    private int id;

    @Schema(description = "Product quantity", defaultValue = "0")
    private int quantity;

    @Schema(description = "Is cart item active", defaultValue = "false")
    @JsonIgnore
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    public Order order;

    @Schema(description = "Product id", defaultValue = "0")
    private long productId;
}
