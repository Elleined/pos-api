package com.elleined.pos_api.mapper.order;

import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.order.OrderedProduct;
import com.elleined.pos_api.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface OrderedProductMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),

            @Mapping(target = "productPriceAtTheTimePurchase", source = "productPriceAtTheTimePurchase"),
            @Mapping(target = "product", source = "product"),
            @Mapping(target = "order", source = "order")
    })
    OrderedProduct toEntity(BigDecimal productPriceAtTheTimePurchase,
                            Product product,
                            Order order);
}
