package com.elleined.pos_api.mapper.store;

import com.elleined.pos_api.dto.store.StoreDTO;
import com.elleined.pos_api.mapper.CustomMapper;
import com.elleined.pos_api.model.store.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StoreMapper extends CustomMapper<Store, StoreDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),

            @Mapping(target = "name", source = "name"),
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "contactNumber", source = "contactNumber"),
    })
    StoreDTO toDTO(Store store);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),

            @Mapping(target = "name", source = "name"),
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "contactNumber", source = "contactNumber"),

            @Mapping(target = "categories", expression = "java(new java.util.ArrayList<>())"),
            @Mapping(target = "staffs", expression = "java(new java.util.ArrayList<>())"),
    })
    Store toEntity(String name,
                   String address,
                   String contactNumber);
}
