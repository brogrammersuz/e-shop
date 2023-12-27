package uz.brogrammers.eshop.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import uz.brogrammers.eshop.product.entity.Product;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@AllArgsConstructor
@Builder
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name="product_id", nullable = false)
    private Integer productId;

}
