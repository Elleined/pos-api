package com.elleined.pos_api.repository.user;

import com.elleined.pos_api.model.user.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface CustomerRepository extends UserRepository<Customer> {

    @Query("SELECT c FROM Customer c WHERE c.createdAt BETWEEN :start AND :end")
    Page<Customer> findAll(@Param("start") LocalDateTime start,
                           @Param("end") LocalDateTime end,
                           Pageable pageable);
}