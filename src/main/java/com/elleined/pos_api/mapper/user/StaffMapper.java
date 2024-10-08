package com.elleined.pos_api.mapper.user;

import com.elleined.pos_api.dto.user.StaffDTO;
import com.elleined.pos_api.mapper.CustomMapper;
import com.elleined.pos_api.mapper.store.StoreMapper;
import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.model.user.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {
                StoreMapper.class
        },
        imports = Staff.Status.class
)
public interface StaffMapper extends CustomMapper<Staff, StaffDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "storeDTO", source = "store"),

            @Mapping(target = "status", source = "status"),
    })
    StaffDTO toDTO(Staff staff);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "store", source = "store"),

            @Mapping(target = "status", expression = "java(Status.ACTIVE)"),
            @Mapping(target = "orders", expression = "java(new java.util.ArrayList<>())")
    })
    Staff toEntity(String name,
                   Store store,
                   String email,
                   String password);
}
