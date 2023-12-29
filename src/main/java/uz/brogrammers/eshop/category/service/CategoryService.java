package uz.brogrammers.eshop.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.eshop.category.entity.Category;
import uz.brogrammers.eshop.category.repository.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public Optional<Category> findById(Integer id){
        return repository.findById(id);
    }

}
