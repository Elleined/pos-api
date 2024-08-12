package com.elleined.pos_api.service.product.category;

import com.elleined.pos_api.exception.resource.ResourceNotFoundException;
import com.elleined.pos_api.mapper.product.CategoryMapper;
import com.elleined.pos_api.model.product.Category;
import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.repository.product.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category save(String name, String description, Store store) {
        Category category = categoryMapper.toEntity(name, description, store);

        categoryRepository.save(category);
        log.debug("Saving category success");
        return category;
    }

    @Override
    public void update(Category category, String name, String description) {
        category.setName(name);
        category.setDescription(description);

        categoryRepository.save(category);
        log.debug("Updating category success.");
    }

    @Override
    public Category getById(int id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with id of " + id + " doesn't exists."));
    }
}
