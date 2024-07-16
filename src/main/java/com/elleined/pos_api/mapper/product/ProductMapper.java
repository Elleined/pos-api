package com.elleined.pos_api.mapper.product;

import com.elleined.pos_api.dto.product.ProductDTO;
import com.elleined.pos_api.mapper.CustomMapper;
import com.elleined.pos_api.model.product.Category;
import com.elleined.pos_api.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.math.BigDecimal;

@Mapper(
        componentModel = "spring",
        uses = {
                CategoryMapper.class
        }
)
public interface ProductMapper extends CustomMapper<Product, ProductDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),

            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "image", source = "image"),
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "categoryDTO", source = "category"),
    })
    ProductDTO toDTO(Product product);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),

            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "image", source = "image"),
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "category", source = "category"),

            @Mapping(target = "orders", expression = "java(new java.util.ArrayList<>())")
    })
    Product toEntity(String name,
                     String description,
                     String image,
                     BigDecimal price,
                     Category category);
}
