package com.elleined.pos_api.controller;

import com.elleined.pos_api.dto.user.StaffDTO;
import com.elleined.pos_api.mapper.user.StaffMapper;
import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.model.user.Staff;
import com.elleined.pos_api.service.store.StoreService;
import com.elleined.pos_api.service.user.staff.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final StaffService staffService;
    private final StaffMapper staffMapper;

    private final StoreService storeService;

    @PostMapping
    public StaffDTO register(@RequestParam("name") String name,
                             @RequestParam("storeId") int storeId,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Store store = storeService.getById(storeId);
        Staff staff = staffService.register(name, store, email, password);

        return staffMapper.toDTO(staff);
    }
}
