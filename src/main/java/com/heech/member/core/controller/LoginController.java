package com.heech.member.core.controller;

import com.heech.member.common.json.JsonResult;
import com.heech.member.core.controller.request.LoginRequest;
import com.heech.member.core.domain.Member;
import com.heech.member.core.service.LoginService;
import com.heech.member.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class LoginController {

    private final LoginService loginService;

    /**
     * 로그인
     */
    @PostMapping(value = "/login")
    public JsonResult login(@RequestBody LoginRequest request,
                            HttpServletRequest httpServletRequest) {

        Member loginMember = loginService.loginMember(request.getLoginId(), request.getPassword());

        if (loginMember == null) {
            return JsonResult.ERROR("존재하지 않는 회원입니다.");
        }

        //로그인 성공처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return JsonResult.OK(loginMember);
    }

    /**
     * 로그아웃
     */
    @PostMapping(value = "/logout")
    public JsonResult logout(HttpServletRequest request) {
        //세션이 있으면 있는 세션 반환, 없으면 null 반환
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return JsonResult.OK();
    }
}
