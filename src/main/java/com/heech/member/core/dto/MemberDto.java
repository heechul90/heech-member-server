package com.heech.member.core.dto;

import com.heech.member.core.domain.Member;
import lombok.Getter;
import org.springframework.util.StringUtils;

import static org.springframework.util.StringUtils.*;
import static org.springframework.util.StringUtils.hasText;

@Getter
public class MemberDto {

    private Long memberId;

    //로그인정보
    private String email;
    private String name;
    private String nickname;

    //OAuth 정보

    //권한정보
    private String role;

    //개인정보
    private String birthday;
    private String gender;
    private String phoneNumber;
    private String address;

    public MemberDto(Member member) {
        this.memberId = member.getId();

        this.email = member.getEmail();
        this.name = member.getName();
        this.nickname = member.getNickname();

        this.role = member.getRole().getName();

        this.birthday = !hasText(member.getBirthday()) ? null : member.getBirthday().substring(0, 4) + "-" + member.getBirthday().substring(4, 6) + "-" + member.getBirthday().substring(6, 8);
        this.gender =  member.getGender() == null ? null : member.getGender().getName();
        this.phoneNumber = member.getMobile() == null ? null : member.getMobile().fullMobileNumber();
        this.address = member.getAddress() == null ? null : member.getAddress().fullAddress();
    }
}
