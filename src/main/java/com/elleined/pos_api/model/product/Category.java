package com.elleined.pos_api.model.product;

import com.elleined.pos_api.model.PrimaryKeyIdentity;
import com.elleined.pos_api.model.store.Store;
import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

@Cacheable
@org.hibernate.annotations.Cache(region = "categoryCache", usage = CacheConcurrencyStrategy.READ_WRITE)

@Entity
@Table(name = "tbl_category")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Category extends PrimaryKeyIdentity {

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "store_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private Store store;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
