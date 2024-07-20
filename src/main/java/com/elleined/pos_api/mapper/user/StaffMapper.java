package com.elleined.pos_api.mapper.user;

import com.elleined.pos_api.model.user.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "name", source = "name"),

            @Mapping(target = "status", source = "status"),
            @Mapping(target = "orders", expression = "java(new java.util.ArrayList<>())")
    })
    Staff toEntity(String name, Staff.Status status);
}
