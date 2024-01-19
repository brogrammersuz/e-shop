package uz.brogrammers.eshop.order.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uz.brogrammers.eshop.order.entity.Order;
import uz.brogrammers.eshop.order.rest.dto.CreateOrderRequest;
import uz.brogrammers.eshop.order.service.OrderItemService;
import uz.brogrammers.eshop.order.service.OrderService;
import uz.brogrammers.eshop.product.service.ProductService;
import uz.brogrammers.eshop.shipping.entity.Shipping;
import uz.brogrammers.eshop.shipping.service.ShippingService;
import uz.brogrammers.eshop.user.entity.User;
import uz.brogrammers.eshop.user.service.UserService;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final UserService userService;
    private final ShippingService shippingService;

    private final ProductService productService;

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Integer userId) {
        return orderService.getAllByUserId(userId);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/")
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @PostMapping("/")
    public Order createOrder(@RequestBody CreateOrderRequest request) {

        User user = userService.findById(request.getUserId()).orElseThrow();
        Shipping shipping = Shipping.builder()
                .address(request.getShipping().getAddress())
                .state(request.getShipping().getState())
                .name(request.getShipping().getName())
                .city(request.getShipping().getCity())
                .build();

        var savedShipping = shippingService.save(shipping);


        request.getItems().stream()
                .forEach(orderItem ->
                {
                    orderItem.setPrice(orderItem.getProduct().getPrice());
                    orderItem.setId(orderItemService.save(orderItem).getId());
                });

        Order order = Order.builder()
                .items(request.getItems())
                .created(new Date())
                .shippingId(savedShipping.getId())
                .user(user)
                .build();

        return orderService.save(order);
    }


}
