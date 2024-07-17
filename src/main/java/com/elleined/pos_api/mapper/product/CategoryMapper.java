package com.elleined.pos_api.mapper.product;

import com.elleined.pos_api.dto.product.CategoryDTO;
import com.elleined.pos_api.mapper.CustomMapper;
import com.elleined.pos_api.model.product.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends CustomMapper<Category, CategoryDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),

            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
    })
    CategoryDTO toDTO(Category category);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),

            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),

            @Mapping(target = "products", expression = "java(new java.util.ArrayList<>())")
    })
    Category toEntity(String name,
                      String description);
}