package com.elleined.pos_api.service.product;

import com.elleined.pos_api.model.product.Category;
import com.elleined.pos_api.model.product.Product;
import com.elleined.pos_api.service.CustomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ProductService extends CustomService<Product> {

    Page<Product> getAll(Category category, Pageable pageable);

    Product save(String name,
                 String description,
                 String image,
                 BigDecimal price);

    void update(String name,
                String description,
                String image,
                BigDecimal price);
}
