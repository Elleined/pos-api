package com.elleined.pos_api.dto.product;

import com.elleined.pos_api.dto.DTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class CategoryDTO extends DTO {

    private String name;
    private String description;
}
