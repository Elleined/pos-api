package com.elleined.pos_api.dto.product;

import com.elleined.pos_api.dto.DTO;
import com.elleined.pos_api.dto.store.StoreDTO;
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
    private StoreDTO storeDTO;
}
