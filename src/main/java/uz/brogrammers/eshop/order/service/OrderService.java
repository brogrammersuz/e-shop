package uz.brogrammers.eshop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.eshop.order.entity.Order;
import uz.brogrammers.eshop.order.repository.OrderRepository;
import uz.brogrammers.eshop.user.entity.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllByUser(User user){
        return orderRepository.findAllByUser(user);
    }

    public Optional<Order> getOrderById(Integer id){
        return orderRepository.findById(id);
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

}
