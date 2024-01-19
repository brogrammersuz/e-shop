package uz.brogrammers.eshop.shoppingcart.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.brogrammers.eshop.product.entity.Product;

@Data
@AllArgsConstructor
public class CreateCartItemRequest {

    private Integer id;

    private Integer quantity;

    private Integer productId;
}
