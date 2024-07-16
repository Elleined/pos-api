package com.elleined.pos_api.model.product;

import com.elleined.pos_api.model.PrimaryKeyIdentity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

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

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
