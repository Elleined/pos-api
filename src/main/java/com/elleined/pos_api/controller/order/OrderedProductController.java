package com.elleined.pos_api.controller.order;

import com.elleined.pos_api.dto.order.OrderedProductDTO;
import com.elleined.pos_api.exception.resource.ResourceNotFoundException;
import com.elleined.pos_api.mapper.order.OrderedProductMapper;
import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.order.OrderedProduct;
import com.elleined.pos_api.model.product.Product;
import com.elleined.pos_api.service.order.OrderService;
import com.elleined.pos_api.service.order.OrderedProductService;
import com.elleined.pos_api.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders/{orderId}/products")
@RequiredArgsConstructor
public class OrderedProductController {
    private final OrderedProductService orderedProductService;
    private final OrderedProductMapper orderedProductMapper;

    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping
    public Page<OrderedProductDTO> getAll(@PathVariable("orderId") int orderId,
                                          @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                          @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                          @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                          @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                          @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Order order = orderService.getById(orderId);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return orderedProductService.getAll(order, pageable)
                .map(orderedProductMapper::toDTO)
                .map(dto -> dto.addLinks(includeRelatedLinks));
    }

    @PostMapping
    public OrderedProductDTO save(@PathVariable("orderId") int orderId,
                                  @RequestParam("productId") int productId,
                                  @RequestParam("quantity") int quantity,
                                  @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Order order = orderService.getById(orderId);
        Product product = productService.getById(productId);

        if (order.has(product)) {
            OrderedProduct orderedProduct = orderedProductService.getByProduct(order, product).orElseThrow(() -> new ResourceNotFoundException("Saving order failed! Cannot find ordered product!"));
            orderedProductService.updateQuantity(orderedProduct);
            return orderedProductMapper.toDTO(orderedProduct);
        }

        OrderedProduct orderedProduct = orderedProductService.save(order, product, quantity);
        return orderedProductMapper.toDTO(orderedProduct).addLinks(includeRelatedLinks);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("orderId") int orderId,
                       @PathVariable("id") int id) {

        Order order = orderService.getById(orderId);
        OrderedProduct orderedProduct = orderedProductService.getById(id);

        orderedProductService.delete(order, orderedProduct);
    }

    @GetMapping("/{id}")
    public OrderedProductDTO getById(@PathVariable("id") int id,
                                     @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        OrderedProduct orderedProduct = orderedProductService.getById(id);
        return orderedProductMapper.toDTO(orderedProduct).addLinks(includeRelatedLinks);
    }
}
