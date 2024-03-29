package uz.brogrammers.eshop.shoppingcart.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created", nullable = false)
    private ZonedDateTime created;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cart_cart_items",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_item_id"))
    private Set<CartItem> items = new HashSet<>();

    public Cart removeItems(){
        this.items.clear();
        return this;
    }

    public Cart removeItem(CartItem item) {
        this.items.remove(item);
        return this;
    }

    public Cart addItem(CartItem item) {
        this.items.add(item);
        return this;
    }

}
