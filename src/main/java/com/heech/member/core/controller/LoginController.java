package com.heech.member.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class LoginController {

    @GetMapping(value = "/login")
    public void login() {

    }

    @PostMapping(value = "/login")
    public void login(HttpServletRequest request) {

    }

    @PostMapping(value = "/logout")
    public void logout(HttpServletRequest request) {

    }
}
