package com.elleined.pos_api.model.order;

import com.elleined.pos_api.model.PrimaryKeyIdentity;
import com.elleined.pos_api.model.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.math.BigDecimal;

@Cacheable
@org.hibernate.annotations.Cache(region = "orderedProductCache", usage = CacheConcurrencyStrategy.READ_WRITE)

@Entity
@Table(name = "tbl_order_product")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OrderedProduct extends PrimaryKeyIdentity {

    @Column(
            name = "quantity",
            nullable = false
    )
    private int quantity;

    @Column(
            name = "product_price_at_the_time_of_purchase",
            nullable = false
    )
    private BigDecimal productPriceAtTheTimePurchase;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private Order order;

    public BigDecimal getAmount() {
        return productPriceAtTheTimePurchase.multiply(new BigDecimal(quantity));
    }
}
