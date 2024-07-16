package com.elleined.pos_api.dto.user;

import com.elleined.pos_api.dto.DTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class UserDTO extends DTO {
    private String name;

    public UserDTO(int id, LocalDateTime createdAt, String name) {
        super(id, createdAt);
        this.name = name;
    }
}
