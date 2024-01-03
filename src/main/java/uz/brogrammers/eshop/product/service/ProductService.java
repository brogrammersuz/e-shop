package uz.brogrammers.eshop.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.eshop.product.mapper.ProductMapper;
import uz.brogrammers.eshop.product.model.ProductModel;
import uz.brogrammers.eshop.product.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::mapToModel)
                .toList();
    }

    public Optional<ProductModel> findById(Integer id) {
        return productRepository.findById(id)
                .map(ProductMapper::mapToModel);
    }

    public void saveProduct(ProductModel productModel) {
        var entity = ProductMapper.mapToEntity(productModel);
        productRepository.save(entity);
    }


}
