package com.heech.member.core.controller;

import com.heech.member.common.json.JsonResult;
import com.heech.member.core.controller.request.JoinMemberRequest;
import com.heech.member.core.domain.Member;
import com.heech.member.core.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user/join")
public class JoinController {

    private final JoinService joinService;

    @PostMapping
    public JsonResult joinMember(@RequestBody @Validated JoinMemberRequest request) {

        //validate
        request.validate();

        Member savedMember = joinService.joinMember(request.toEntity());
        return JsonResult.OK();
    }
}
