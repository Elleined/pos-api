package com.elleined.pos_api.model.user;

import com.elleined.pos_api.model.PrimaryKeyIdentity;
import com.elleined.pos_api.model.store.Store;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class User extends PrimaryKeyIdentity {

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "store_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private Store store;
}
