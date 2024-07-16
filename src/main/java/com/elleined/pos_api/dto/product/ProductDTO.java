package com.elleined.pos_api.dto.product;

import com.elleined.pos_api.dto.DTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProductDTO extends DTO {

    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private CategoryDTO categoryDTO;

    @Builder
    public ProductDTO(int id, LocalDateTime createdAt, String name, String description, String image, BigDecimal price, CategoryDTO categoryDTO) {
        super(id, createdAt);
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.categoryDTO = categoryDTO;
    }

    @Override
    public ProductDTO addLinks(boolean doInclude) {
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
