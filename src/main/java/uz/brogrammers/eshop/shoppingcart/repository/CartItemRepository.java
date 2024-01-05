package uz.brogrammers.eshop.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brogrammers.eshop.shoppingcart.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
