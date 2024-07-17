package com.elleined.pos_api.controller.order;

import com.elleined.pos_api.dto.order.OrderDTO;
import com.elleined.pos_api.mapper.order.OrderMapper;
import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.user.Customer;
import com.elleined.pos_api.model.user.Staff;
import com.elleined.pos_api.service.order.OrderService;
import com.elleined.pos_api.service.user.customer.CustomerService;
import com.elleined.pos_api.service.user.staff.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    private final CustomerService customerService;
    private final StaffService staffService;

    @PostMapping
    public OrderDTO save(@RequestParam("staffId") int staffId,
                         @RequestParam("customerId") int customerId) {

        Staff staff = staffService.getById(staffId);
        Customer customer = customerService.getById(customerId);

        Order order = orderService.save(staff, customer);
        return orderMapper.toDTO(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        Order order = orderService.getById(id);
        orderService.delete(order);
    }

    @PatchMapping("/{id}")
    public void updateStatus(@PathVariable("id") int id,
                             @RequestParam("status") Order.Status status) {

        Order order = orderService.getById(id);
        orderService.updateStatus(order, status);
    }

    @GetMapping("/{id}")
    public OrderDTO getById(@PathVariable("id") int id) {
        Order order = orderService.getById(id);
        return orderMapper.toDTO(order);
    }
}
