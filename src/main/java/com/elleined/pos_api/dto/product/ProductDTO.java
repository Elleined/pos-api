package com.elleined.pos_api.dto.product;

import com.elleined.pos_api.dto.DTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductDTO extends DTO {

    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private CategoryDTO categoryDTO;

}
