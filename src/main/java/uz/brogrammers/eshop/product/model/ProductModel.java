package uz.brogrammers.eshop.product.model;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProductModel {

    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer categoryId;
    private String imageUrl;

}
