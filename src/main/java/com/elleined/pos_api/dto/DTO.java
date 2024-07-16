package com.elleined.pos_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class DTO extends HateoasDTO {
    private int id;
    private LocalDateTime createdAt;

    public DTO(int id, LocalDateTime createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }
}
