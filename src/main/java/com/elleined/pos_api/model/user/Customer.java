package com.elleined.pos_api.model.user;

import com.elleined.pos_api.model.order.Order;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

@Cacheable
@org.hibernate.annotations.Cache(region = "customerCache", usage = CacheConcurrencyStrategy.READ_WRITE)

@Entity
@Table(name = "tbl_customer")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Customer extends User {

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
