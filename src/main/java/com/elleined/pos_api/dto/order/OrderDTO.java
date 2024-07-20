package com.elleined.pos_api.dto.order;

import com.elleined.pos_api.dto.DTO;
import com.elleined.pos_api.dto.user.CustomerDTO;
import com.elleined.pos_api.dto.user.StaffDTO;
import com.elleined.pos_api.model.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OrderDTO extends DTO {
    private Order.Status status;
    private CustomerDTO customerDTO;
    private StaffDTO staffDTO;
}
