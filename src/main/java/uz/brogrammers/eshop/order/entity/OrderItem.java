package uz.brogrammers.eshop.order.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.brogrammers.eshop.product.entity.Product;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinTable(name="order_item_product",
            joinColumns = @JoinColumn(name ="item_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Product product;

}
