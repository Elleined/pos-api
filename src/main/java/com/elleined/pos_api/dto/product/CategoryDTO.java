package com.elleined.pos_api.dto.product;

import com.elleined.pos_api.dto.DTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CategoryDTO extends DTO {

    private String name;
    private String description;

    @Builder
    public CategoryDTO(int id, LocalDateTime createdAt, String name, String description) {
        super(id, createdAt);
        this.name = name;
        this.description = description;
    }

    @Override
    public CategoryDTO addLinks(boolean doInclude) {
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
