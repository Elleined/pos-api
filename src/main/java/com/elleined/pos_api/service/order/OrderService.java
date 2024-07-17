package com.elleined.pos_api.service.order;

import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.user.Customer;
import com.elleined.pos_api.model.user.Staff;
import com.elleined.pos_api.service.CustomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService extends CustomService<Order> {
    Page<Order> getAll(Customer customer, Pageable pageable);
    Page<Order> getAll(Staff staff, Pageable pageable);
    Order save(Staff staff, Customer customer);
    void delete(Order order);
    void updateStatus(Order order, Order.Status status);
}
