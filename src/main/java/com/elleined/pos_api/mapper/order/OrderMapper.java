package com.elleined.pos_api.mapper.order;

import com.elleined.pos_api.dto.order.OrderDTO;
import com.elleined.pos_api.mapper.CustomMapper;
import com.elleined.pos_api.model.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderMapper extends CustomMapper<Order, OrderDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),
    })
    OrderDTO toDTO(Order order);
}
