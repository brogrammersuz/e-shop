package uz.brogrammers.eshop.order.rest.dto;

import lombok.Getter;
import lombok.Setter;
import uz.brogrammers.eshop.order.entity.OrderItem;
import uz.brogrammers.eshop.shipping.entity.Shipping;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CreateOrderRequest {
    private Integer userId;
    private Set<OrderItem> items = new HashSet<OrderItem>();
    private Shipping shipping;
}
