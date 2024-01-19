package uz.brogrammers.eshop.shoppingcart.entity;


import jakarta.persistence.*;
import lombok.*;
import uz.brogrammers.eshop.product.entity.Product;

@Entity
@Table(name = "cart_item")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinTable(name="cart_item_product",
            joinColumns = @JoinColumn(name ="item_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Product product;
}
