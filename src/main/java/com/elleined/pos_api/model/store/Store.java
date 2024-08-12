package com.elleined.pos_api.model.store;

import com.elleined.pos_api.model.PrimaryKeyIdentity;
import com.elleined.pos_api.model.product.Category;
import com.elleined.pos_api.model.user.Staff;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

@Cacheable
@org.hibernate.annotations.Cache(region = "storeCache", usage = CacheConcurrencyStrategy.READ_WRITE)

@Entity
@Table(name = "tbl_store")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Store extends PrimaryKeyIdentity {

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "address",
            nullable = false
    )
    private String address;

    @Column(
            name = "contact_number",
            unique = true,
            nullable = false
    )
    private String contactNumber;

    @OneToMany(mappedBy = "store")
    private List<Category> categories;

    @OneToMany(mappedBy = "store")
    private List<Staff> staffs;
}
