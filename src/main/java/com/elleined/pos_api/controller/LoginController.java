package com.elleined.pos_api.controller;

import com.elleined.pos_api.service.user.staff.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final StaffService staffService;

    @PostMapping
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password) {

        return staffService.login(email, password);
    }
}
