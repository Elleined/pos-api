package com.elleined.pos_api.mapper.product;

import com.elleined.pos_api.dto.product.ProductDTO;
import com.elleined.pos_api.mapper.CustomMapper;
import com.elleined.pos_api.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMapper extends CustomMapper<Product, ProductDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),
    })
    ProductDTO toDTO(Product product);
}
