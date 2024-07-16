package com.elleined.pos_api.model.order;

import com.elleined.pos_api.model.PrimaryKeyIdentity;
import com.elleined.pos_api.model.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_order_product")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OrderedProduct extends PrimaryKeyIdentity {

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
}
