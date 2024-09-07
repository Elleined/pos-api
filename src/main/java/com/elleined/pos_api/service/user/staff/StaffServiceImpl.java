package com.elleined.pos_api.service.user.staff;

import com.elleined.pos_api.exception.resource.ResourceNotFoundException;
import com.elleined.pos_api.jwt.JWTService;
import com.elleined.pos_api.mapper.user.StaffMapper;
import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.model.user.Staff;
import com.elleined.pos_api.repository.user.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<Staff> getAll(Staff.Status status, Pageable pageable) {
        return staffRepository.findAll(status, pageable);
    }

    @Override
    public Staff getByJWT(String jwt) throws ResourceNotFoundException {
        String email = jwtService.getEmail(jwt);
        return staffRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with email of " + email + " doesn't exists!"));
    }

    @Override
    public void updateStatus(Staff staff, Staff.Status status) {
        staff.setStatus(status);

        staffRepository.save(staff);
        log.debug("Updating status success");
    }

    @Override
    public String login(String email,
                        String password) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        if (!authentication.isAuthenticated())
            throw new UsernameNotFoundException("Invalid credential");

        return jwtService.generateToken(email);
    }

    @Override
    public Staff getById(int id) {
        return staffRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Staff with id of " + id + " doesn't exists!"));
    }

    @Override
    public Staff register(String name, Store store, String email, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        Staff staff = staffMapper.toEntity(name, store, email, hashedPassword);

        staffRepository.save(staff);
        log.debug("Saving staff success!");
        return staff;
    }

    @Override
    public Page<Staff> getAll(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }
}
