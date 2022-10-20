package com.heech.member.core.controller;

import com.heech.member.common.json.JsonResult;
import com.heech.member.core.controller.request.LoginMemberRequest;
import com.heech.member.core.controller.request.SignupMemberRequest;
import com.heech.member.core.controller.response.SignupMemberResponse;
import com.heech.member.core.domain.Member;
import com.heech.member.core.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public JsonResult signup(@RequestBody SignupMemberRequest request) {
        Member signupMember = authService.signup(request.toMember(passwordEncoder));
        return JsonResult.OK(new SignupMemberResponse(signupMember.getId()));
    }

    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginMemberRequest request) {
        authService.login(request.getLoginId(), request.getPassword());
        return JsonResult.OK();
    }
}
