package com.elleined.pos_api.mapper.order;

import com.elleined.pos_api.dto.order.OrderProductDTO;
import com.elleined.pos_api.mapper.CustomMapper;
import com.elleined.pos_api.model.order.OrderedProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderProductMapper extends CustomMapper<OrderedProduct, OrderProductDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),
    })
    OrderProductDTO toDTO(OrderedProduct orderedProduct);
}
