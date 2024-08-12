package com.elleined.pos_api.dto.user;

import com.elleined.pos_api.dto.store.StoreDTO;
import com.elleined.pos_api.model.user.Staff;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class StaffDTO extends UserDTO {
    private Staff.Status status;

    @Builder
    public StaffDTO(int id, LocalDateTime createdAt, String name, StoreDTO storeDTO, Staff.Status status) {
        super(id, createdAt, name, storeDTO);
        this.status = status;
    }

    @Override
    public StaffDTO addLinks(boolean doInclude) {
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
