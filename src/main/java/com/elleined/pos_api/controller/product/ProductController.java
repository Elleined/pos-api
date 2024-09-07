package com.elleined.pos_api.controller.product;

import com.elleined.pos_api.dto.product.ProductDTO;
import com.elleined.pos_api.mapper.product.ProductMapper;
import com.elleined.pos_api.model.product.Category;
import com.elleined.pos_api.model.product.Product;
import com.elleined.pos_api.service.product.ProductService;
import com.elleined.pos_api.service.product.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/categories/{categoryId}/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    private final CategoryService categoryService;

    @GetMapping
    public Page<ProductDTO> getAll(@PathVariable("categoryId") int categoryId,
                                   @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                   @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                   @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                   @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy) {

        Category category = categoryService.getById(categoryId);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return productService.getAll(category, pageable)
                .map(productMapper::toDTO);
    }

    @PostMapping
    public ProductDTO save(@PathVariable("categoryId") int categoryId,
                           @RequestParam("name") String name,
                           @RequestParam("description") String description,
                           @RequestParam("image") String image,
                           @RequestParam("price") BigDecimal price) {

        Category category = categoryService.getById(categoryId);

        Product product = productService.save(category, name, description, image, price);
        return productMapper.toDTO(product);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id,
                       @RequestParam("name") String name,
                       @RequestParam("description") String description,
                       @RequestParam("image") String image,
                       @RequestParam("price") BigDecimal price) {

        Product product = productService.getById(id);
        productService.update(product, name, description, image, price);
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable("id") int id) {

        Product product = productService.getById(id);
        return productMapper.toDTO(product);
    }
}
