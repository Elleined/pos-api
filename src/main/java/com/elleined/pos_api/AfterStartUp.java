package com.elleined.pos_api;

import com.elleined.pos_api.mapper.order.OrderMapper;
import com.elleined.pos_api.mapper.order.OrderedProductMapper;
import com.elleined.pos_api.mapper.product.CategoryMapper;
import com.elleined.pos_api.mapper.product.ProductMapper;
import com.elleined.pos_api.mapper.store.StoreMapper;
import com.elleined.pos_api.mapper.user.CustomerMapper;
import com.elleined.pos_api.mapper.user.StaffMapper;
import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.populator.Populator;
import com.elleined.pos_api.repository.order.OrderRepository;
import com.elleined.pos_api.repository.order.OrderedProductRepository;
import com.elleined.pos_api.repository.product.CategoryRepository;
import com.elleined.pos_api.repository.product.ProductRepository;
import com.elleined.pos_api.repository.store.StoreRepository;
import com.elleined.pos_api.repository.user.CustomerRepository;
import com.elleined.pos_api.repository.user.StaffRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AfterStartUp {
    private final Faker faker;
    private final PasswordEncoder passwordEncoder;

    private final StoreMapper storeMapper;
    private final CustomerMapper customerMapper;
    private final StaffMapper staffMapper;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private final OrderedProductMapper orderedProductMapper;

    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderedProductRepository orderedProductRepository;

    @PostConstruct
    void init() {
        if (customerRepository.existsById(1))
            return;

        Populator storePopulator = () -> storeRepository.saveAll(List.of(
                storeMapper.toEntity(faker.company().name(), faker.address().fullAddress(), faker.phoneNumber().phoneNumber())
        ));

        Populator customerPopulator = () -> customerRepository.saveAll(List.of(
                customerMapper.toEntity(faker.name().fullName(), storeRepository.findById(1).orElseThrow()),
                customerMapper.toEntity(faker.name().fullName(), storeRepository.findById(1).orElseThrow()),
                customerMapper.toEntity(faker.name().fullName(), storeRepository.findById(1).orElseThrow()),
                customerMapper.toEntity(faker.name().fullName(), storeRepository.findById(1).orElseThrow())
        ));

        Populator staffPopulator = () -> staffRepository.saveAll(List.of(
                staffMapper.toEntity(faker.name().fullName(), storeRepository.findById(1).orElseThrow(), faker.bothify("##??@gmail.com"), passwordEncoder.encode("user")),
                staffMapper.toEntity(faker.name().fullName(), storeRepository.findById(1).orElseThrow(), faker.bothify("##??@gmail.com"), passwordEncoder.encode("user"))
        ));

        Populator categoryPopulator = () -> categoryRepository.saveAll(List.of(
                categoryMapper.toEntity(faker.name().fullName(), faker.lorem().sentence(), storeRepository.findById(1).orElseThrow()),
                categoryMapper.toEntity(faker.name().fullName(), faker.lorem().sentence(), storeRepository.findById(1).orElseThrow())
        ));

        Populator productPopulator = () -> productRepository.saveAll(List.of(
                productMapper.toEntity(faker.name().fullName(), faker.lorem().sentence(), faker.internet().image(), new BigDecimal(faker.number().randomNumber()), categoryRepository.findById(1).orElseThrow()),
                productMapper.toEntity(faker.name().fullName(), faker.lorem().sentence(), faker.internet().image(), new BigDecimal(faker.number().randomNumber()), categoryRepository.findById(1).orElseThrow()),
                productMapper.toEntity(faker.name().fullName(), faker.lorem().sentence(), faker.internet().image(), new BigDecimal(faker.number().randomNumber()), categoryRepository.findById(2).orElseThrow()),
                productMapper.toEntity(faker.name().fullName(), faker.lorem().sentence(), faker.internet().image(), new BigDecimal(faker.number().randomNumber()), categoryRepository.findById(2).orElseThrow())
        ));

        Populator orderPopulator = () -> orderRepository.saveAll(List.of(
                orderMapper.toEntity(Order.Status.PENDING, customerRepository.findById(1).orElseThrow(), staffRepository.findById(1).orElseThrow()),
                orderMapper.toEntity(Order.Status.COMPLETED, customerRepository.findById(1).orElseThrow(), staffRepository.findById(1).orElseThrow())
        ));

        Populator orderedProductPopulator = () -> orderedProductRepository.saveAll(List.of(
                orderedProductMapper.toEntity(new BigDecimal(faker.number().randomNumber()), productRepository.findById(1).orElseThrow(), orderRepository.findById(1).orElseThrow(), (int) faker.number().randomNumber()),
                orderedProductMapper.toEntity(new BigDecimal(faker.number().randomNumber()), productRepository.findById(1).orElseThrow(), orderRepository.findById(2).orElseThrow(), (int) faker.number().randomNumber()),
                orderedProductMapper.toEntity(new BigDecimal(faker.number().randomNumber()), productRepository.findById(2).orElseThrow(), orderRepository.findById(1).orElseThrow(), (int) faker.number().randomNumber()),
                orderedProductMapper.toEntity(new BigDecimal(faker.number().randomNumber()), productRepository.findById(2).orElseThrow(), orderRepository.findById(2).orElseThrow(), (int) faker.number().randomNumber()),
                orderedProductMapper.toEntity(new BigDecimal(faker.number().randomNumber()), productRepository.findById(3).orElseThrow(), orderRepository.findById(1).orElseThrow(), (int) faker.number().randomNumber()),
                orderedProductMapper.toEntity(new BigDecimal(faker.number().randomNumber()), productRepository.findById(3).orElseThrow(), orderRepository.findById(2).orElseThrow(), (int) faker.number().randomNumber()),
                orderedProductMapper.toEntity(new BigDecimal(faker.number().randomNumber()), productRepository.findById(4).orElseThrow(), orderRepository.findById(1).orElseThrow(), (int) faker.number().randomNumber()),
                orderedProductMapper.toEntity(new BigDecimal(faker.number().randomNumber()), productRepository.findById(4).orElseThrow(), orderRepository.findById(2).orElseThrow(), (int) faker.number().randomNumber())
        ));

        log.debug("Please wait saving default values...");
        storePopulator.populate();
        customerPopulator.populate();
        staffPopulator.populate();
        categoryPopulator.populate();
        productPopulator.populate();
        orderPopulator.populate();
        orderedProductPopulator.populate();
        log.debug("Saving default values success...");
    }
}
