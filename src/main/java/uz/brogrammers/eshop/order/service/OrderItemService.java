package uz.brogrammers.eshop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.eshop.order.entity.OrderItem;
import uz.brogrammers.eshop.order.repository.OrderItemRepository;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItem save(OrderItem item){
        return orderItemRepository.save(item);
    }

}
