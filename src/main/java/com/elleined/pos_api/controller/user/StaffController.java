package com.elleined.pos_api.controller.user;

import com.elleined.pos_api.dto.order.OrderDTO;
import com.elleined.pos_api.dto.user.StaffDTO;
import com.elleined.pos_api.mapper.order.OrderMapper;
import com.elleined.pos_api.mapper.user.StaffMapper;
import com.elleined.pos_api.model.user.Staff;
import com.elleined.pos_api.service.order.OrderService;
import com.elleined.pos_api.service.user.staff.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staffs")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;
    private final StaffMapper staffMapper;

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/orders")
    public Page<OrderDTO> getAll(@RequestHeader("Authorization") String jwt,
                                 @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                 @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                 @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                 @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy) {

        Staff staff = staffService.getByJWT(jwt);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return orderService.getAll(staff, pageable)
                .map(orderMapper::toDTO);
    }

    @GetMapping
    public Page<StaffDTO> getAll(@RequestParam(required = false, name = "status") Staff.Status status,
                                 @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                 @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                 @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                 @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);

        if (status == null)
            return staffService.getAll(pageable)
                    .map(staffMapper::toDTO);

        return staffService.getAll(status, pageable)
                .map(staffMapper::toDTO);
    }

    @PatchMapping("/{id}")
    public void updateStatus(@PathVariable("id") int staffId,
                             @RequestParam("status") Staff.Status status) {

        Staff staff = staffService.getById(staffId);
        staffService.updateStatus(staff, status);
    }

    @GetMapping("/{id}")
    public StaffDTO getById(@PathVariable("id") int staffId) {

        Staff staff = staffService.getById(staffId);
        return staffMapper.toDTO(staff);
    }
}
