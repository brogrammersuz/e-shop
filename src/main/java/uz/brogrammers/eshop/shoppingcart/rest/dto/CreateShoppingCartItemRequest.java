package uz.brogrammers.eshop.shoppingcart.rest.dto;

import lombok.Data;

@Data
public class CreateShoppingCartItemRequest {

    private Integer id;

    private Integer quantity;

    private Integer productId;

    public CreateShoppingCartItemRequest(Integer id, Integer quantity, Integer productId) {
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
    }
}
