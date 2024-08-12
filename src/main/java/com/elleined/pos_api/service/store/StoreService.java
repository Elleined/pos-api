package com.elleined.pos_api.service.store;

import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.service.CustomService;

public interface StoreService extends CustomService<Store> {
    Store save(String name,
               String address,
               String contactNumber);
}
