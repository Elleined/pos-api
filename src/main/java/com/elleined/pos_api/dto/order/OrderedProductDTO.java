package com.elleined.pos_api.dto.order;

import com.elleined.pos_api.dto.DTO;
import com.elleined.pos_api.dto.product.ProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OrderedProductDTO extends DTO {
    private int quantity;
    private BigDecimal productPriceAtTheTimePurchase;
    private ProductDTO productDTO;
    private OrderDTO orderDTO;
}
