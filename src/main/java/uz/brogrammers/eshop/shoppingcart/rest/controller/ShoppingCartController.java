package uz.brogrammers.eshop.shoppingcart.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uz.brogrammers.eshop.shoppingcart.entity.ShoppingCart;
import uz.brogrammers.eshop.shoppingcart.entity.ShoppingCartItem;
import uz.brogrammers.eshop.shoppingcart.rest.dto.CreateShoppingCartItemRequest;
import uz.brogrammers.eshop.shoppingcart.rest.dto.CreateShoppingCartRequest;
import uz.brogrammers.eshop.shoppingcart.service.ShoppingCartService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping("/{id}")
    public ShoppingCart getById(@PathVariable Integer id) {
        return shoppingCartService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public List<ShoppingCart> getAll() {
        return shoppingCartService.findAll();
    }

    @PostMapping("/")
    public ShoppingCart create(@RequestBody CreateShoppingCartRequest request) {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCreated(ZonedDateTime.now());

        return shoppingCartService.save(shoppingCart);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        shoppingCartService.deleteById(id);
    }

    @GetMapping("/{cartId}/items/{productId}")
    public CreateShoppingCartItemRequest getShoppingCartItem(@PathVariable("cartId") Integer cartId,
                                                             @PathVariable("productId") Integer productId) {
        Set<ShoppingCartItem> items = shoppingCartService.findById(cartId)
                .map(ShoppingCart::getItems)
                .orElseThrow();

        return items.stream()
                .filter(item -> item.getProductId() == productId)
                .map(item -> new CreateShoppingCartItemRequest(
                        item.getId(), item.getQuantity(), item.getProductId()
                ))
                .findFirst()
                .orElse(null);
    }

}
