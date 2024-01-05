package uz.brogrammers.eshop.shoppingcart.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_item")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "product_id", nullable = false)
    private Integer productId;
}
