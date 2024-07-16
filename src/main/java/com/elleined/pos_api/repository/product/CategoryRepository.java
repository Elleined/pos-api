package com.elleined.pos_api.repository.product;

import com.elleined.pos_api.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}