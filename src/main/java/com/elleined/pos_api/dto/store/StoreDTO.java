package com.elleined.pos_api.dto.store;

import com.elleined.pos_api.dto.DTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class StoreDTO extends DTO {

    private String name;
    private String address;
    private String contactNumber;

}
