package com.elleined.pos_api.model.user;

import com.elleined.pos_api.model.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

@Cacheable
@org.hibernate.annotations.Cache(region = "staffCache", usage = CacheConcurrencyStrategy.READ_WRITE)

@Entity
@Table(name = "tbl_staff")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Staff extends User {

    @Column(
            name = "status",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "staff")
    private List<Order> orders;

    public enum Status {
        ACTIVE,
        IN_ACTIVE
    }
}
