package uz.brogrammers.eshop.order.rest.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.brogrammers.eshop.order.entity.OrderItem;
import uz.brogrammers.eshop.shipping.entity.Shipping;
import uz.brogrammers.eshop.user.entity.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponse {

    private Integer id;
    private Date created;
    private User user;
    private Set<OrderItem> items = new HashSet<>();

    private Shipping shipping;

}
