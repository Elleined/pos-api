package com.elleined.pos_api.service.user.customer;

import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.model.user.Customer;
import com.elleined.pos_api.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface CustomerService extends UserService<Customer> {
    Customer save(String name, Store store);
    Customer update(Customer user, String name);
    Page<Customer> getAll(LocalDateTime start,
                          LocalDateTime end,
                          Pageable pageable);
}
