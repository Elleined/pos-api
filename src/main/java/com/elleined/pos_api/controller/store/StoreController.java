package com.elleined.pos_api.controller.store;

import com.elleined.pos_api.dto.store.StoreDTO;
import com.elleined.pos_api.mapper.store.StoreMapper;
import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    private final StoreMapper storeMapper;

    @PostMapping
    public StoreDTO save(@RequestParam("name") String name,
                         @RequestParam("address") String address,
                         @RequestParam("contactNumber") String contactNumber) {

        Store store = storeService.save(name, address, contactNumber);

        return storeMapper.toDTO(store);
    }

    @GetMapping("/{id}")
    public StoreDTO getById(@PathVariable("id") int id) {
        Store store = storeService.getById(id);
        return storeMapper.toDTO(store);
    }
}
