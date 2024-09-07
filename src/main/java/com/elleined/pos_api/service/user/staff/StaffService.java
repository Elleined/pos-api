package com.elleined.pos_api.service.user.staff;

import com.elleined.pos_api.exception.resource.ResourceNotFoundException;
import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.model.user.Staff;
import com.elleined.pos_api.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StaffService extends UserService<Staff> {
    Page<Staff> getAll(Staff.Status status, Pageable pageable);
    Staff getByJWT(String jwt) throws ResourceNotFoundException;

    void updateStatus(Staff staff, Staff.Status status);

    Staff register(String name,
                   Store store,
                   String email,
                   String password);

    String login(String email,
                 String password);
}
