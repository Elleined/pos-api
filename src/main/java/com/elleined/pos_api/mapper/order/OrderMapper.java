package com.elleined.pos_api.mapper.order;

import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.user.Customer;
import com.elleined.pos_api.model.user.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderMapper {

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
