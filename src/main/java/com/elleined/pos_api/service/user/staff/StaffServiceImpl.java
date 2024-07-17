package com.elleined.pos_api.service.user.staff;

import com.elleined.pos_api.exception.resource.ResourceNotFoundException;
import com.elleined.pos_api.mapper.user.StaffMapper;
import com.elleined.pos_api.model.user.Staff;
import com.elleined.pos_api.model.user.User;
import com.elleined.pos_api.repository.user.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    @Override
    public Page<Staff> getAll(Staff.Status status, Pageable pageable) {
        return staffRepository.findAll(status, pageable);
    }

    @Override
    public void updateStatus(Staff staff, Staff.Status status) {
        staff.setStatus(status);

        staffRepository.save(staff);
        log.debug("Updating status success");
    }

    @Override
    public Staff getById(int id) {
        return staffRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Staff with id of " + id + " doesn't exists!"));
    }

    @Override
    public Staff save(String name) {
        Staff staff = staffMapper.toEntity(name, Staff.Status.ACTIVE);

        staffRepository.save(staff);
        log.debug("Saving staff success!");
        return staff;
    }

    @Override
    public Staff update(Staff staff, String name) {
        staff.setName(name);

        staffRepository.save(staff);
        log.debug("Updating staff success.");
        return staff;
    }

    @Override
    public Page<Staff> getAll(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }
}
