package uz.brogrammers.eshop.shoppingcart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.eshop.shoppingcart.entity.CartItem;
import uz.brogrammers.eshop.shoppingcart.repository.CartItemRepository;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItem save(CartItem item){
        return cartItemRepository.save(item);
    }

    public void delete(CartItem item){
        cartItemRepository.delete(item);
    }


}
