package com.elleined.pos_api.repository.product;

import com.elleined.pos_api.model.product.Category;
import com.elleined.pos_api.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    Page<Product> findAll(@Param("category") Category category, Pageable pageable);
}