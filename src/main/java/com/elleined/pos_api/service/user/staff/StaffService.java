package com.elleined.pos_api.service.user.staff;

import com.elleined.pos_api.model.user.Staff;
import com.elleined.pos_api.service.CustomService;
import com.elleined.pos_api.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StaffService extends UserService<Staff> {
    Page<Staff> getAll(Staff.Status status, Pageable pageable);
    void updateStatus(Staff staff, Staff.Status status);
}
