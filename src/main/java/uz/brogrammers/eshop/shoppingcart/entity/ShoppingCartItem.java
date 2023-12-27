package uz.brogrammers.eshop.shoppingcart.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import uz.brogrammers.eshop.product.entity.Product;

import java.math.BigDecimal;

@Entity
@Table(name = "shopping_cart_item")
@AllArgsConstructor
@Builder
@Getter
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name="product_id", nullable = false)
    private Integer productId;
}
