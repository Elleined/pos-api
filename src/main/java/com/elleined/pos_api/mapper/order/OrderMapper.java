package com.elleined.pos_api.mapper.order;

import com.elleined.pos_api.dto.order.OrderDTO;
import com.elleined.pos_api.mapper.CustomMapper;
import com.elleined.pos_api.mapper.user.CustomerMapper;
import com.elleined.pos_api.mapper.user.StaffMapper;
import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.user.Customer;
import com.elleined.pos_api.model.user.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {
                CustomerMapper.class,
                StaffMapper.class
        }
)
public interface OrderMapper extends CustomMapper<Order, OrderDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),

            @Mapping(target = "status", source = "status"),
            @Mapping(target = "customerDTO", source = "customer"),
            @Mapping(target = "staffDTO", source = "staff"),
    })
    OrderDTO toDTO(Order order);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),

            @Mapping(target = "status", source = "status"),
            @Mapping(target = "customer", source = "customer"),
            @Mapping(target = "staff", source = "staff"),

            @Mapping(target = "orderedProducts", expression = "java(new java.util.ArrayList<>())")
    })
    Order toEntity(Order.Status status,
                   Customer customer,
                   Staff staff);
}
