package com.heech.member.core.controller;

import com.heech.member.common.json.JsonResult;
import com.heech.member.common.util.SecurityUtil;
import com.heech.member.core.controller.request.LoginMemberRequest;
import com.heech.member.core.controller.request.SignupMemberRequest;
import com.heech.member.core.controller.response.SignupMemberResponse;
import com.heech.member.core.domain.Member;
import com.heech.member.core.dto.MemberDto;
import com.heech.member.core.service.AuthService;
import com.heech.member.core.service.MemberService;
import com.heech.member.jwt.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public JsonResult signup(@RequestBody SignupMemberRequest request) {
        Member signupMember = authService.signup(request.toMember(passwordEncoder));
        return JsonResult.OK(new SignupMemberResponse(signupMember.getId()));
    }

    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginMemberRequest request) {
        TokenDto token = authService.login(request.getLoginId(), request.getPassword());
        return JsonResult.OK(token);
    }

    @GetMapping(value = "/info")
    public JsonResult info() {
        Member findMember = memberService.findMember(SecurityUtil.getCurrentMemberId());
        return JsonResult.OK(new MemberDto(findMember));
    }
}