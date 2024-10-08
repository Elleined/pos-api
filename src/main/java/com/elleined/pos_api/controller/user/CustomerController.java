package com.elleined.pos_api.controller.user;

import com.elleined.pos_api.dto.order.OrderDTO;
import com.elleined.pos_api.dto.user.CustomerDTO;
import com.elleined.pos_api.mapper.order.OrderMapper;
import com.elleined.pos_api.mapper.user.CustomerMapper;
import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.model.user.Customer;
import com.elleined.pos_api.service.order.OrderService;
import com.elleined.pos_api.service.store.StoreService;
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

    private final StoreService storeService;

    @GetMapping("/{id}/orders")
    public Page<OrderDTO> getAll(@PathVariable("id") int customerId,
                                 @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                 @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                 @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                 @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy) {

        Customer customer = customerService.getById(customerId);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return orderService.getAll(customer, pageable)
                .map(orderMapper::toDTO);
    }

    @GetMapping("/ranged")
    public Page<CustomerDTO> getAll(@RequestParam("start") LocalDateTime start,
                                    @RequestParam("end") LocalDateTime end,
                                    @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                    @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                    @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                    @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return customerService.getAll(start, end, pageable)
                .map(customerMapper::toDTO);
    }

    @PostMapping
    public CustomerDTO save(@RequestParam("name") String name,
                            @RequestParam("storeId") int storeId) {

        Store store = storeService.getById(storeId);
        Customer customer = customerService.save(name, store);

        return customerMapper.toDTO(customer);
    }

    @PutMapping("/{id}")
    public CustomerDTO update(@PathVariable("id") int customerId,
                              @RequestParam("name") String name) {

        Customer customer = customerService.getById(customerId);

        customerService.update(customer, name);
        return customerMapper.toDTO(customer);
    }

    @GetMapping
    public Page<CustomerDTO> getAll(@RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                    @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                    @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                    @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return customerService.getAll(pageable)
                .map(customerMapper::toDTO);
    }

    @GetMapping("/{id}")
    public CustomerDTO getById(@PathVariable("id") int id) {

        Customer customer = customerService.getById(id);
        return customerMapper.toDTO(customer);
    }
}
