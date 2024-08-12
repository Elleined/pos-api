package com.elleined.pos_api.dto.store;

import com.elleined.pos_api.dto.DTO;
import com.elleined.pos_api.dto.HateoasDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class StoreDTO extends DTO {

    private String name;
    private String address;
    private String contactNumber;

    @Builder
    public StoreDTO(int id, LocalDateTime createdAt, String name, String address, String contactNumber) {
        super(id, createdAt);
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    @Override
    public StoreDTO addLinks(boolean doInclude) {
        super.addLinks(doInclude);
        return this;
    }

    @Override
    protected List<Link> getAllRelatedLinks(boolean doInclude) {
        return List.of();
    }

    @Override
    protected List<Link> getAllSelfLinks(boolean doInclude) {
        return List.of();
    }
}
