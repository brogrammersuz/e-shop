package uz.brogrammers.eshop.shoppingcart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.eshop.shoppingcart.entity.Cart;
import uz.brogrammers.eshop.shoppingcart.repository.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Optional<Cart> findById(Integer id) {
        return cartRepository.findById(id);
    }

    public Cart save(Cart cart){
        return cartRepository.save(cart);
    }

    public void deleteById(Integer id){
        cartRepository.deleteById(id);
    }

}
