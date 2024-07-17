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

    @GetMapping("/{id}/orders")
    public Page<OrderDTO> getAll(@RequestParam("id") int staffId,
                                 @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                 @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                 @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                 @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                 @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Staff staff = staffService.getById(staffId);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return orderService.getAll(staff, pageable)
                .map(orderMapper::toDTO)
                .map(dto -> dto.addLinks(includeRelatedLinks));
    }

    @GetMapping
    public Page<StaffDTO> getAll(@RequestParam("status") Staff.Status status,
                                 @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                 @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                 @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                 @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                 @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);

        if (status == null)
            return staffService.getAll(pageable)
                    .map(staffMapper::toDTO)
                    .map(dto -> dto.addLinks(includeRelatedLinks));


        return staffService.getAll(status, pageable)
                .map(staffMapper::toDTO)
                .map(dto -> dto.addLinks(includeRelatedLinks));
    }

    @PatchMapping("/{id}")
    public void updateStatus(@PathVariable("id") int staffId,
                             @RequestParam("status") Staff.Status status) {

        Staff staff = staffService.getById(staffId);
        staffService.updateStatus(staff, status);
    }

    @PostMapping
    public StaffDTO save(@RequestParam("name") String name,
                         @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Staff staff = staffService.save(name);
        return staffMapper.toDTO(staff).addLinks(includeRelatedLinks);
    }

    @PutMapping("/{id}")
    public StaffDTO update(@PathVariable("id") int staffId,
                           @RequestParam("name") String name,
                           @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Staff staff = staffService.getById(staffId);

        staffService.update(staff, name);
        return staffMapper.toDTO(staff).addLinks(includeRelatedLinks);
    }

    @GetMapping("/{id}")
    public StaffDTO getById(@PathVariable("id") int staffId,
                            @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Staff staff = staffService.getById(staffId);
        return staffMapper.toDTO(staff).addLinks(includeRelatedLinks);
    }
}
