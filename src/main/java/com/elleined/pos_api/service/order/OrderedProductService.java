package com.elleined.pos_api.service.order;

import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.order.OrderedProduct;
import com.elleined.pos_api.model.product.Product;
import com.elleined.pos_api.service.CustomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderedProductService extends CustomService<OrderedProduct> {
    Page<OrderedProduct> getAll(Order order, Pageable pageable);
    OrderedProduct save(Order order, Product product, int quantity);
    void delete(Order order, OrderedProduct orderedProduct);

    void updateQuantity(OrderedProduct orderedProduct, int quantity);
    Optional<OrderedProduct> getByProduct(Order order, Product product);
}
