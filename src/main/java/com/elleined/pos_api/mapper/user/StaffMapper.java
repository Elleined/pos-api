package com.elleined.pos_api.mapper.user;

import com.elleined.pos_api.dto.user.StaffDTO;
import com.elleined.pos_api.mapper.CustomMapper;
import com.elleined.pos_api.model.user.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StaffMapper extends CustomMapper<Staff, StaffDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),
    })
    StaffDTO toDTO(Staff staff);
}
