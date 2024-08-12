package com.elleined.pos_api.controller.store;

import com.elleined.pos_api.dto.store.StoreDTO;
import com.elleined.pos_api.mapper.store.StoreMapper;
import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    private final StoreMapper storeMapper;

    @PostMapping
    public StoreDTO save(String name,
                         String address,
                         String contactNumber) {

        Store store = storeService.save(name, address, contactNumber);

        return storeMapper.toDTO(store);
    }

    @GetMapping
    public StoreDTO getById(int id) {
        Store store = storeService.getById(id);
        return storeMapper.toDTO(store);
    }
}
