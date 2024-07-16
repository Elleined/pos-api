package com.elleined.pos_api.service.product.category;

import com.elleined.pos_api.model.product.Category;
import com.elleined.pos_api.service.CustomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService extends CustomService<Category> {
    Page<Category> getAll(Pageable pageable);
    Category save(String name, String description);
    void update(String name, String description);
}
