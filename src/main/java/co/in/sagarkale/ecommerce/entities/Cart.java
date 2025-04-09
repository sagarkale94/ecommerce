package co.in.sagarkale.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    @Schema(description = "Cart id", defaultValue = "0")
    private int id;

    @Schema(description = "Is cart active", defaultValue = "false")
    @JsonIgnore
    private boolean isActive;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
