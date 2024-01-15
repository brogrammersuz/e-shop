package uz.brogrammers.eshop.product.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
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

    @GetMapping("/")
    public List<ProductResponse> getAll() {
        return productService.getAllProducts().stream()
                .map(ProductMapper::mapToDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Integer id) {
        return productService.findById(id)
                .map(ProductMapper::mapToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

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

        productService.saveProduct(productModel);

    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable("id") Integer id,
                              @RequestParam(value = "file", required = false) MultipartFile file,
                              @RequestParam("name") String name,
                              @RequestParam("price") BigDecimal price,
                              @RequestParam("categoryId") Integer categoryId)
            throws FileNotFoundException {

        Category category = categoryService.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Invalid category is selected"));

        ProductModel oldProduct = productService.findById(id).orElseThrow(() -> new IllegalArgumentException("Product by id " + id + " not found"));
        String fileName = "";

        if (file != null) {
            fileName = fileStorageService.storeFile(file);
            String url = oldProduct.getImageUrl();
            String oldFileName = url.substring(url.lastIndexOf('/') + 1, url.length());
            fileStorageService.deleteFile(oldFileName);
        }

        var productModel = ProductModel.builder()
                .id(id)
                .name(name)
                .price(price)
                .categoryId(categoryId)
                .imageUrl(file != null ? "http://localhost:8081/api/files/download/" + fileName : oldProduct.getImageUrl())
                .build();

        this.productService.saveProduct(productModel);
    }

}
