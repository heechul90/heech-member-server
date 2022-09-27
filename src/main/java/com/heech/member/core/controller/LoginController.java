package com.heech.member.core.controller;

import com.heech.member.core.controller.request.LoginRequest;
import com.heech.member.core.domain.Member;
import com.heech.member.core.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class LoginController {

    private final LoginService loginService;

    @GetMapping(value = "/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest request) {

        Member loginMember = loginService.loginMember(request.getLoginId(), request.getPassword());

        if (loginMember == null) {
            return null;
        }

        return null;
    }

    @PostMapping(value = "/login")
    public void login(HttpServletRequest request) {

    }

    @PostMapping(value = "/logout")
    public void logout(HttpServletRequest request) {

    }
}
