package com.elleined.pos_api.model.order;

import com.elleined.pos_api.model.PrimaryKeyIdentity;
import com.elleined.pos_api.model.product.Product;
import com.elleined.pos_api.model.user.Customer;
import com.elleined.pos_api.model.user.Staff;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tbl_order")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Order extends PrimaryKeyIdentity {

    @Column(
            name = "status",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "staff_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private Staff staff;

    @OneToMany(mappedBy = "order")
    private List<OrderedProduct> orderedProducts;

    public boolean has(OrderedProduct orderedProduct) {
        return this.getOrderedProducts().contains(orderedProduct);
    }
    public boolean has(Product product) {
        return this.getOrderedProducts().stream()
                .map(OrderedProduct::getProduct)
                .anyMatch(product::equals);
    }


    public enum Status {
        PENDING,
        COMPLETED
    }

    public BigDecimal getTotal() {
        return this.getOrderedProducts().stream()
                .map(OrderedProduct::getAmount)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }
}
