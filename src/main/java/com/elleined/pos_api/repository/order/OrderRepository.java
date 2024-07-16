package com.elleined.pos_api.repository.order;

import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.user.Customer;
import com.elleined.pos_api.model.user.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.customer = :customer")
    Page<Order> findAll(@Param("customer") Customer customer, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.staff = :staff")
    Page<Order> findAll(@Param("staff") Staff staff, Pageable pageable);
}