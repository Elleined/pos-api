package com.elleined.pos_api.service.store;

import com.elleined.pos_api.exception.resource.ResourceNotFoundException;
import com.elleined.pos_api.mapper.store.StoreMapper;
import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    @Override
    public Store save(String name, String address, String contactNumber) {
        Store store = storeMapper.toEntity(name, address, contactNumber);
        storeRepository.save(store);
        log.debug("Saving store success");
        return store;
    }

    @Override
    public Store getById(int id) {
        return storeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Store with id of " + id + " doesn't exists."));
    }
}
