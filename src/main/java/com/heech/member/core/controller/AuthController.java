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
import com.heech.member.config.jwt.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public JsonResult signup(@RequestBody @Validated SignupMemberRequest request) {
        Member signupMember = authService.signup(request.toMember(passwordEncoder));
        return JsonResult.OK(new SignupMemberResponse(signupMember.getId()));
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody @Validated LoginMemberRequest request) {
        TokenDto token = authService.login(request.getEmail(), request.getPassword());
        return JsonResult.OK(token);
    }

    /**
     * access token 정보 조회
     */
    @GetMapping(value = "/info")
    public JsonResult info() {
        Member findMember = memberService.findMember(SecurityUtil.getCurrentMemberId());
        return JsonResult.OK(new MemberDto(findMember));
    }
}
