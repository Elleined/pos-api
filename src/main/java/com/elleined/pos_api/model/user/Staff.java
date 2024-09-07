package com.elleined.pos_api.model.user;

import com.elleined.pos_api.model.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tbl_staff")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Staff extends User implements UserDetails {

    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(name = "password")
    private String password;

    @Column(
            name = "status",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "staff")
    private List<Order> orders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public enum Status {
        ACTIVE,
        IN_ACTIVE
    }
}
