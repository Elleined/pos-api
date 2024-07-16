package com.elleined.pos_api.repository.order;

import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.order.OrderedProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Integer> {

    @Query("SELECT op FROM OrderedProduct op WHERE op.order = :order")
    Page<OrderedProduct> findAll(@Param("order") Order order, Pageable pageable);
}