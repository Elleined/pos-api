package com.elleined.pos_api.dto.order;

import com.elleined.pos_api.dto.DTO;
import com.elleined.pos_api.dto.user.CustomerDTO;
import com.elleined.pos_api.dto.user.StaffDTO;
import com.elleined.pos_api.model.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDTO extends DTO {
    private Order.Status status;
    private CustomerDTO customerDTO;
    private StaffDTO staffDTO;

    @Builder
    public OrderDTO(int id, LocalDateTime createdAt, Order.Status status, CustomerDTO customerDTO, StaffDTO staffDTO) {
        super(id, createdAt);
        this.status = status;
        this.customerDTO = customerDTO;
        this.staffDTO = staffDTO;
    }

    @Override
    public OrderDTO addLinks(boolean doInclude) {
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
