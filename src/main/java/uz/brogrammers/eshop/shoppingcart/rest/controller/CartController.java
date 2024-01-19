package uz.brogrammers.eshop.shoppingcart.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.brogrammers.eshop.product.mapper.ProductMapper;
import uz.brogrammers.eshop.product.model.ProductModel;
import uz.brogrammers.eshop.product.service.ProductService;
import uz.brogrammers.eshop.shoppingcart.entity.Cart;
import uz.brogrammers.eshop.shoppingcart.entity.CartItem;
import uz.brogrammers.eshop.shoppingcart.rest.dto.CreateCartItemRequest;
import uz.brogrammers.eshop.shoppingcart.service.CartItemService;
import uz.brogrammers.eshop.shoppingcart.service.CartService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shopping-cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final CartItemService cartItemService;

    @GetMapping("/{id}")
    public Cart getShoppingCartById(@PathVariable Integer id) {

        return cartService.findById(id).orElse(null);
    }

    @GetMapping("/")
    public List<Cart> getAll() {
        return cartService.findAll();
    }

    @PostMapping("/")
    public Cart createShoppingCart() {

        Cart cart = new Cart();
        cart.setCreated(ZonedDateTime.now());

        return cartService.save(cart);

    }

    @DeleteMapping("/{id}")
    public void deleteShoppingCart(@PathVariable Integer id) {

        cartService.deleteById(id);

    }

    @GetMapping("/{cartId}/items/{productId}")
    public CreateCartItemRequest getShoppingCartItem(@PathVariable("cartId") Integer cartId,
                                                     @PathVariable("productId") Integer productId) {
        Set<CartItem> items = cartService.findById(cartId).orElse(null).getItems();
        for (CartItem cartItem : items) {
            if (cartItem.getProduct().getId() == productId) {
                return new CreateCartItemRequest(cartItem.getId(), cartItem.getQuantity(),
                        cartItem.getProduct().getId());
            }
        }

        return null;
    }

    @PostMapping("/{cartId}/items")
    public Cart createShoppingCartItem(@PathVariable Integer cartId,
                                       @RequestBody CreateCartItemRequest request) {
        ProductModel product = this.productService.findById(request.getProductId()).orElse(null);
        CartItem result;
        if (product != null) {
            CartItem item = new CartItem();
            item.setQuantity(request.getQuantity());
            item.setProduct(ProductMapper.mapToEntity(product));
            result = cartItemService.save(item);
            Cart cart = cartService.findById(cartId).get().addItem(result);
            return cartService.save(cart);
        }

        return null;
    }

    @PutMapping("/{cartId}/items/{productId}")
    public Cart updateShoppingCartItem(@PathVariable Integer cartId, @PathVariable Integer productId,
                                       @RequestBody CreateCartItemRequest request) {
        Set<CartItem> items = cartService.findById(cartId).get().getItems();
        for (CartItem item : items) {
            if (item.getProduct().getId() == productId) {
                item.setQuantity(request.getQuantity());
                cartItemService.save(item);
            }
        }

        return cartService.findById(cartId).get();
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public Cart deleteShoppingCartItem(@PathVariable Integer cartId, @PathVariable Integer productId) {
        Cart cart = cartService.findById(cartId).get();
        if (cart != null) {
            Set<CartItem> items = cart.getItems();
            List<CartItem> curItems = items.stream().filter(item -> item.getProduct().getId().equals(productId)).collect(Collectors.toList());

            for (CartItem item : curItems) {
                cart = cart.removeItem(item);
                cartItemService.delete(item);
            }
        }

        return cartService.save(cart);

    }

    @DeleteMapping("/{cartId}/clear")
    public Cart clearShoppingCart(@PathVariable Integer cartId) {
        Cart cart = cartService.findById(cartId).get();
        if (cart != null) {
            Set<CartItem> items = cart.getItems();
            cart = cart.removeItems();

            for (CartItem item : items) {
                cartItemService.delete(item);
            }
        }

        return cartService.save(cart);
    }

}
