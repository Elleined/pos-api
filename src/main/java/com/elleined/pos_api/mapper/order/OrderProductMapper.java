package com.elleined.pos_api.mapper.order;

import com.elleined.pos_api.dto.order.OrderProductDTO;
import com.elleined.pos_api.mapper.CustomMapper;
import com.elleined.pos_api.mapper.product.ProductMapper;
import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.order.OrderedProduct;
import com.elleined.pos_api.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.math.BigDecimal;

@Mapper(
        componentModel = "spring",
        uses = {
                ProductMapper.class,
                OrderMapper.class
        }
)
public interface OrderProductMapper extends CustomMapper<OrderedProduct, OrderProductDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),

            @Mapping(target = "productPriceAtTheTimePurchase", source = "productPriceAtTheTimePurchase"),
            @Mapping(target = "productDTO", source = "product"),
            @Mapping(target = "orderDTO", source = "order")
    })
    OrderProductDTO toDTO(OrderedProduct orderedProduct);

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
