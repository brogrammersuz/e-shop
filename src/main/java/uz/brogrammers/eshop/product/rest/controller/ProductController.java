package uz.brogrammers.eshop.product.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.brogrammers.eshop.category.entity.Category;
import uz.brogrammers.eshop.category.service.CategoryService;
import uz.brogrammers.eshop.common.service.FileStorageService;
import uz.brogrammers.eshop.product.mapper.ProductMapper;
import uz.brogrammers.eshop.product.model.ProductModel;
import uz.brogrammers.eshop.product.rest.dto.ProductResponse;
import uz.brogrammers.eshop.product.service.ProductService;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    private final FileStorageService fileStorageService;

    @GetMapping("/all")
    public List<ProductResponse> getAll() {
        return productService.getAllProducts().stream()
                .map(ProductMapper::mapToDto)
                .toList();
    }

    @PostMapping("/")
    public void addProduct(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
                           @RequestParam BigDecimal price, @RequestParam Integer categoryId) throws FileNotFoundException {

        Category category = categoryService.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Invalid category is selected"));
        var imageUrl = fileStorageService.storeFile(file);

        var productModel = ProductModel.builder()
                .categoryId(categoryId)
                .name(name)
                .price(price)
                .imageUrl(imageUrl)
                .build();

        productService.addProduct(productModel);

    }

}
