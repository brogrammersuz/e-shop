package uz.brogrammers.eshop.product.mapper;

import uz.brogrammers.eshop.product.entity.Product;
import uz.brogrammers.eshop.product.model.ProductModel;
import uz.brogrammers.eshop.product.rest.dto.ProductResponse;

public class ProductMapper {

    public static ProductModel mapToModel(Product entity){
        return ProductModel.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .categoryId(entity.getCategoryId())
                .imageUrl(entity.getImageUrl())
                .build();
    }

    public static Product mapToEntity(ProductModel model){
        return Product.builder()
                .id(model.getId())
                .title(model.getTitle())
                .price(model.getPrice())
                .categoryId(model.getCategoryId())
                .imageUrl(model.getImageUrl())
                .build();
    }

    public static ProductResponse mapToDto(ProductModel model){
        return ProductResponse.builder()
                .id(model.getId())
                .title(model.getTitle())
                .price(model.getPrice())
                .categoryId(model.getCategoryId())
                .imageUrl(model.getImageUrl())
                .build();
    }

}
