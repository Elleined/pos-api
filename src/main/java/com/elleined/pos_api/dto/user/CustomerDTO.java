package com.elleined.pos_api.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CustomerDTO extends UserDTO {

    @Builder
    public CustomerDTO(int id, LocalDateTime createdAt, String name) {
        super(id, createdAt, name);
    }

    @Override
    public CustomerDTO addLinks(boolean doInclude) {
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
