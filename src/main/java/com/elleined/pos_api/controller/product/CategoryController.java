package com.elleined.pos_api.controller.product;

import com.elleined.pos_api.dto.product.CategoryDTO;
import com.elleined.pos_api.mapper.product.CategoryMapper;
import com.elleined.pos_api.model.product.Category;
import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.service.product.category.CategoryService;
import com.elleined.pos_api.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    private final StoreService storeService;

    @GetMapping
    public Page<CategoryDTO> getAll(@RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                    @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                    @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                    @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return categoryService.getAll(pageable)
                .map(categoryMapper::toDTO);
    }

    @PostMapping
    public CategoryDTO save(@RequestParam("name") String name,
                            @RequestParam("description") String description,
                            @RequestParam("storeId") int storeId) {

        Store store = storeService.getById(storeId);
        Category category = categoryService.save(name, description, store);
        return categoryMapper.toDTO(category);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id,
                       @RequestParam("name") String name,
                       @RequestParam("description") String description) {

        Category category = categoryService.getById(id);
        categoryService.update(category, name, description);
    }

    @GetMapping("/{id}")
    public CategoryDTO getById(@PathVariable("id") int id) {

        Category category = categoryService.getById(id);
        return categoryMapper.toDTO(category);
    }
}
