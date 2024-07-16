package com.elleined.pos_api.model.user;

import com.elleined.pos_api.model.PrimaryKeyIdentity;
import jakarta.persistence.Column;
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
}
