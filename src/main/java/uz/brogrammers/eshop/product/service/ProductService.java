package uz.brogrammers.eshop.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.eshop.product.mapper.ProductMapper;
import uz.brogrammers.eshop.product.model.ProductModel;
import uz.brogrammers.eshop.product.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::mapToModel)
                .toList();
    }

    public void addProduct(ProductModel productModel) {
        var entity = ProductMapper.mapToEntity(productModel);
        productRepository.save(entity);
    }


}
