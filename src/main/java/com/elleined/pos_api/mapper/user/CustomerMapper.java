package com.elleined.pos_api.mapper.user;

import com.elleined.pos_api.dto.user.CustomerDTO;
import com.elleined.pos_api.mapper.CustomMapper;
import com.elleined.pos_api.mapper.store.StoreMapper;
import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.model.user.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {
                StoreMapper.class
        }
)
public interface CustomerMapper extends CustomMapper<Customer, CustomerDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "storeDTO", source = "store")
    })
    CustomerDTO toDTO(Customer customer);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "store", source = "store"),

            @Mapping(target = "orders", expression = "java(new java.util.ArrayList<>())")
    })
    Customer toEntity(String name,
                      Store store);
}
