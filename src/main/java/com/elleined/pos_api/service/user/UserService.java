package com.elleined.pos_api.service.user;

import com.elleined.pos_api.model.user.User;
import com.elleined.pos_api.service.CustomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService<T extends User> extends CustomService<T> {
    Page<T> getAll(Pageable pageable);
}
