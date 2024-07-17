package com.elleined.pos_api.service.order;

import com.elleined.pos_api.exception.resource.ResourceNotFoundException;
import com.elleined.pos_api.mapper.order.OrderMapper;
import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.user.Customer;
import com.elleined.pos_api.model.user.Staff;
import com.elleined.pos_api.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Page<Order> getAll(Customer customer, Pageable pageable) {
        return orderRepository.findAll(customer, pageable);
    }

    @Override
    public Page<Order> getAll(Staff staff, Pageable pageable) {
        return orderRepository.findAll(staff, pageable);
    }

    @Override
    public Order save(Staff staff, Customer customer) {
        Order order = orderMapper.toEntity(Order.Status.PENDING, customer, staff);

        orderRepository.save(order);
        log.debug("Saving order success");
        return order;
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
        log.debug("Order deleted successfully.");
    }

    @Override
    public void updateStatus(Order order, Order.Status status) {
        order.setStatus(status);

        orderRepository.save(order);
        log.debug("Updating order status success.");
    }

    @Override
    public Order getById(int id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order with id of " + id + " doesn't exists."));
    }
}
