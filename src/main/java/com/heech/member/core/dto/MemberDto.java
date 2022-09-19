package com.heech.member.core.dto;

import com.heech.member.core.domain.Member;
import lombok.Getter;

import static org.springframework.util.StringUtils.hasText;

@Getter
public class MemberDto {

    private Long memberId;

    //로그인정보
    private String loginId;
    private String name;
    private String email;

    //권한정보
    private String role;

    //개인정보
    private String birthday;
    private String gender;
    private String phoneNumber;
    private String address;
    private String profileImage;

    public MemberDto(Member member) {
        this.memberId = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.role = member.getRole().getName();
        this.birthday = member.getBirthday().substring(0, 4) + "-" + member.getBirthday().substring(4, 6) + "-" + member.getBirthday().substring(6, 8);
        this.gender = member.getGender().getName();
        this.phoneNumber = member.getMobile().fullMobileNumber();
        this.address = member.getAddress().fullAddress();
        this.profileImage = hasText(member.getProfileImage()) ? member.getProfileImage() : "no-image";
    }
}
