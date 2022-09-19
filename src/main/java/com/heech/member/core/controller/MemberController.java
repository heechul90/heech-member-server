package com.heech.member.core.controller;

import com.heech.member.common.json.JsonResult;
import com.heech.member.core.controller.request.CreateMemberRequest;
import com.heech.member.core.controller.request.UpdateMemberRequest;
import com.heech.member.core.controller.response.CreateMemberResponse;
import com.heech.member.core.controller.response.UpdateMemberResponse;
import com.heech.member.core.domain.Member;
import com.heech.member.core.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * 멤버 목록 조회
     */

    /**
     * 멤버 단건 조회
     */

    /**
     * 멤버 저장
     */
    @PostMapping
    public JsonResult saveMember(@RequestBody @Validated CreateMemberRequest request) {

        //validate
        request.validate();

        //save
        Member savedMember = memberService.saveMember(request.toEntity());

        return JsonResult.OK(new CreateMemberResponse(savedMember.getId()));

    }

    /**
     * 멤버 수정
     */
    @PutMapping(value = "/{memberId}")
    public JsonResult updateMember(@PathVariable("memberId") Long memberId,
                                   @RequestBody @Validated UpdateMemberRequest request) {

        //validate
        request.validate();

        //update
        memberService.updateMember(memberId, request.toUpdateMemberParam());
        Member updatedMember = memberService.findMember(memberId);

        return JsonResult.OK(new UpdateMemberResponse(updatedMember.getId()));
    }

    /**
     * 멤버 삭제
     */

}
