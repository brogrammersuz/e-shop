package uz.brogrammers.eshop.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brogrammers.eshop.shoppingcart.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
