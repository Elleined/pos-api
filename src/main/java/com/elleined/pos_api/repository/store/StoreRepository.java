package com.elleined.pos_api.repository.store;

import com.elleined.pos_api.model.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}