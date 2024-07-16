package com.elleined.pos_api.model.product;

import com.elleined.pos_api.model.PrimaryKeyIdentity;
import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.order.OrderedProduct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Product extends PrimaryKeyIdentity {

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(
            name = "price",
            nullable = false
    )
    private BigDecimal price;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderedProduct> orders;
}
