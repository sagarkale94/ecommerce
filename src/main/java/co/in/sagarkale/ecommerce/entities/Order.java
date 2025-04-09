package co.in.sagarkale.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    @Schema(description = "Order id", defaultValue = "0")
    private int id;

    @Schema(description = "Is order active", defaultValue = "false")
    @JsonIgnore
    private boolean isActive;

    @Schema(description = "Order date time")
    private LocalDateTime date;

    @Schema(description = "Order status")
    private String status;

    @Schema(description = "Order amount")
    private double amount;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
