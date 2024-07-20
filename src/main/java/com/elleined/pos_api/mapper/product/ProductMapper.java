package com.elleined.pos_api.mapper.product;

import com.elleined.pos_api.model.product.Category;
import com.elleined.pos_api.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface ProductMapper {

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
