package com.elleined.pos_api.dto.order;

import com.elleined.pos_api.dto.DTO;
import com.elleined.pos_api.dto.product.ProductDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderedProductDTO extends DTO {
    private BigDecimal productPriceAtTheTimePurchase;
    private ProductDTO productDTO;
    private OrderDTO orderDTO;

    @Builder
    public OrderedProductDTO(int id, LocalDateTime createdAt, BigDecimal productPriceAtTheTimePurchase, ProductDTO productDTO, OrderDTO orderDTO) {
        super(id, createdAt);
        this.productPriceAtTheTimePurchase = productPriceAtTheTimePurchase;
        this.productDTO = productDTO;
        this.orderDTO = orderDTO;
    }

    @Override
    public OrderedProductDTO addLinks(boolean doInclude) {
        super.addLinks(doInclude);
        return this;
    }

    @Override
    protected List<Link> getAllRelatedLinks(boolean doInclude) {
        return List.of();
    }

    @Override
    protected List<Link> getAllSelfLinks(boolean doInclude) {
        return List.of();
    }
}
