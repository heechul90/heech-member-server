package com.heech.member.core.controller;

import com.heech.member.common.json.JsonResult;
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

    @PostMapping(value = "/login")
    public JsonResult login(@RequestBody LoginRequest request) {

        Member loginMember = loginService.loginMember(request.getLoginId(), request.getPassword());

        if (loginMember == null) {
            return null;
        }


        return JsonResult.OK(loginMember);
    }

    @PostMapping(value = "/logout")
    public void logout(HttpServletRequest request) {

    }
}
