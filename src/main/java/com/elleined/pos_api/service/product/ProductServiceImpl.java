package com.elleined.pos_api.service.product;

import com.elleined.pos_api.exception.resource.ResourceNotFoundException;
import com.elleined.pos_api.mapper.product.ProductMapper;
import com.elleined.pos_api.model.product.Category;
import com.elleined.pos_api.model.product.Product;
import com.elleined.pos_api.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<Product> getAll(Category category, Pageable pageable) {
        return productRepository.findAll(category, pageable);
    }

    @Override
    public Product save(Category category, String name, String description, String image, BigDecimal price) {
        Product product = productMapper.toEntity(name, description, image, price, category);

        productRepository.save(product);
        log.debug("Saving product success.");
        return product;
    }

    @Override
    public void update(Product product, String name, String description, String image, BigDecimal price) {
        product.setName(name);
        product.setDescription(description);
        product.setImage(image);
        product.setPrice(price);

        productRepository.save(product);
        log.debug("Updating product success.");
    }

    @Override
    public Product getById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id of " + id + " doesn't exists."));
    }
}
