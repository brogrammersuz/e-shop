package uz.brogrammers.eshop.shoppingcart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.eshop.shoppingcart.entity.ShoppingCart;
import uz.brogrammers.eshop.shoppingcart.repository.ShoppingCartRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

    public Optional<ShoppingCart> findById(Integer id) {
        return shoppingCartRepository.findById(id);
    }

    public ShoppingCart save(ShoppingCart cart){
        return shoppingCartRepository.save(cart);
    }

    public void deleteById(Integer id){
        shoppingCartRepository.deleteById(id);
    }

}
