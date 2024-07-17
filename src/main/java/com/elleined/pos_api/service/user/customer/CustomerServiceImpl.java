package com.elleined.pos_api.service.user.customer;

import com.elleined.pos_api.exception.resource.ResourceNotFoundException;
import com.elleined.pos_api.mapper.user.CustomerMapper;
import com.elleined.pos_api.model.user.Customer;
import com.elleined.pos_api.repository.user.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Page<Customer> getAll(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return customerRepository.findAll(start, end, pageable);
    }

    @Override
    public Customer getById(int id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id of " + id + " doesn't exists!"));
    }

    @Override
    public Customer save(String name) {
        Customer customer = customerMapper.toEntity(name);

        customerRepository.save(customer);
        log.debug("Saving customer success");
        return customer;
    }

    @Override
    public Customer update(Customer user, String name) {
        user.setName(name);

        customerRepository.save(user);
        log.debug("Updating customer success.");
        return user;
    }

    @Override
    public Page<Customer> getAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
