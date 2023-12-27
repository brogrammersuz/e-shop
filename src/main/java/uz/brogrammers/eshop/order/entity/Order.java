package uz.brogrammers.eshop.order.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order")
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created", nullable = false)
    private ZonedDateTime created;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<OrderItem> items = new HashSet<>();

    @Column(name = "shipping_id")
    private Integer shippingId;
}
