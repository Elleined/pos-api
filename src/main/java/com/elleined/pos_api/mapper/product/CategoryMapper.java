package com.elleined.pos_api.mapper.product;

import com.elleined.pos_api.dto.product.CategoryDTO;
import com.elleined.pos_api.mapper.CustomMapper;
import com.elleined.pos_api.mapper.user.CustomerMapper;
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
    })
    CategoryDTO toDTO(Category category);
}
