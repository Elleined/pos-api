package com.elleined.pos_api.mapper;

public interface CustomMapper<ENTITY, DTO extends com.elleined.pos_api.dto.DTO> {
    DTO toDTO(ENTITY entity);
}