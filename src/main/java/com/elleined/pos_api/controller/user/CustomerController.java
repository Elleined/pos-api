package com.elleined.pos_api.controller.user;

import com.elleined.pos_api.dto.order.OrderDTO;
import com.elleined.pos_api.dto.user.CustomerDTO;
import com.elleined.pos_api.mapper.order.OrderMapper;
import com.elleined.pos_api.mapper.user.CustomerMapper;
import com.elleined.pos_api.model.user.Customer;
import com.elleined.pos_api.service.order.OrderService;
import com.elleined.pos_api.service.user.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/{id}/orders")
    public Page<OrderDTO> getAll(@RequestParam("id") int customerId,
                                 @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                 @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                 @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                 @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                 @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Customer customer = customerService.getById(customerId);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return orderService.getAll(customer, pageable)
                .map(orderMapper::toDTO)
                .map(dto -> dto.addLinks(includeRelatedLinks));
    }

    @GetMapping("/ranged")
    public Page<CustomerDTO> getAll(@RequestParam("start") LocalDateTime start,
                                    @RequestParam("end") LocalDateTime end,
                                    @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                    @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                    @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                    @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                    @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return customerService.getAll(start, end, pageable)
                .map(customerMapper::toDTO)
                .map(dto -> dto.addLinks(includeRelatedLinks));
    }

    @PostMapping
    public CustomerDTO save(@RequestParam("name") String name,
                            @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Customer customer = customerService.save(name);
        return customerMapper.toDTO(customer).addLinks(includeRelatedLinks);
    }

    @PutMapping("/{id}")
    public CustomerDTO update(@PathVariable("id") int customerId,
                              @RequestParam("name") String name,
                              @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Customer customer = customerService.getById(customerId);

        customerService.update(customer, name);
        return customerMapper.toDTO(customer).addLinks(includeRelatedLinks);
    }

    @GetMapping
    public Page<CustomerDTO> getAll(@RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                    @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                    @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                    @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                    @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return customerService.getAll(pageable)
                .map(customerMapper::toDTO)
                .map(dto -> dto.addLinks(includeRelatedLinks));
    }

    @GetMapping("/{id}")
    public CustomerDTO getById(@PathVariable("id") int id,
                               @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Customer customer = customerService.getById(id);
        return customerMapper.toDTO(customer).addLinks(includeRelatedLinks);
    }
}
