package uz.brogrammers.eshop.shoppingcart.rest.dto;

import lombok.Data;

@Data
public class CreateCartItemRequest {

    private Integer id;

    private Integer quantity;

    private Integer productId;

    public CreateCartItemRequest(Integer id, Integer quantity, Integer productId) {
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
    }
}
