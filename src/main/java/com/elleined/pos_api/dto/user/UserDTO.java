package com.elleined.pos_api.dto.user;

import com.elleined.pos_api.dto.DTO;
import com.elleined.pos_api.dto.store.StoreDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class UserDTO extends DTO {
    private String name;
    private StoreDTO storeDTO;

    public UserDTO(int id, LocalDateTime createdAt, String name, StoreDTO storeDTO) {
        super(id, createdAt);
        this.name = name;
        this.storeDTO = storeDTO;
    }
}
