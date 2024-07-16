package com.elleined.pos_api.repository.user;

import com.elleined.pos_api.model.user.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StaffRepository extends UserRepository<Staff> {

    @Query("SELECT s FROM Staff s WHERE s.status = :status")
    Page<Staff> findAll(@Param("status") Staff.Status status, Pageable pageable);
}