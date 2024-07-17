package com.elleined.pos_api.service.order;

import com.elleined.pos_api.exception.resource.ResourceNotFoundException;
import com.elleined.pos_api.exception.resource.ResourceNotOwnedException;
import com.elleined.pos_api.mapper.order.OrderedProductMapper;
import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.order.OrderedProduct;
import com.elleined.pos_api.model.product.Product;
import com.elleined.pos_api.repository.order.OrderedProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class OrderedProductServiceImpl implements OrderedProductService {
    private final OrderedProductRepository orderedProductRepository;
    private final OrderedProductMapper orderedProductMapper;

    @Override
    public Page<OrderedProduct> getAll(Order order, Pageable pageable) {
        return orderedProductRepository.findAll(order, pageable);
    }

    @Override
    public OrderedProduct save(Order order, Product product) {
        BigDecimal currentProductPrice = product.getPrice();
        OrderedProduct orderedProduct = orderedProductMapper.toEntity(currentProductPrice, product, order);

        orderedProductRepository.save(orderedProduct);
        log.debug("Saving ordered product success.");
        return orderedProduct;
    }

    @Override
    public void delete(Order order, OrderedProduct orderedProduct) {
        if (!order.has(orderedProduct))
            throw new ResourceNotOwnedException("Cannot delete this ordered product! because this order doesn't have the specified ordered product.");

        order.getOrderedProducts().remove(orderedProduct);

        orderedProductRepository.delete(orderedProduct);
        log.debug("Ordered product deleted.");
    }

    @Override
    public OrderedProduct getById(int id) {
        return orderedProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ordered Product with id of " + id + " doesn't exists."));
    }
}
