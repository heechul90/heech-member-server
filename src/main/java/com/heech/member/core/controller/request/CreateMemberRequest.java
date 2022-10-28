package com.heech.member.core.controller.request;

import com.heech.member.common.json.ErrorCode;
import com.heech.member.core.domain.*;
import com.heech.member.common.exception.JsonInvalidRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateMemberRequest {

    //로그인정보
    @NotBlank
    private String username;
    @NotBlank(message = "이메일을 입력하세요.")
    //@Email
    private String email;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
    @NotBlank(message = "이름을 입력하세요.")
    private String name;
    private String nickname;

    //권한정보
    @NotNull
    private Role role;

    //개인정보
    @NotEmpty(message = "생년월일 연도를 선택하세요.")
    private String birthdayYear;
    @NotEmpty(message = "생년월일 월을 선택하세요.")
    private String birthdayMonth;
    @NotEmpty(message = "생년월일 일을 입력하세요.")
    private String birthdayDay;

    @NotNull(message = "성별을 선택하세요.")
    private Gender gender;

    @NotEmpty(message = "휴대폰번호 첫번째자리를 선택하세요.")
    private String phoneNumberFirst;
    @NotEmpty(message = "휴대폰번호 중간자리를 입력하세요.")
    private String phoneNumberMiddle;
    @NotEmpty(message = "휴대폰번호 마지막자리를 입력하세요.")
    private String phoneNumberLast;

    @NotEmpty(message = "우편번호를 입력하세요.")
    private String zipcode;
    @NotEmpty(message = "주소를 입력하세요.")
    private String address;
    @NotEmpty(message = "상세주소를 입력하세요.")
    private String addressDetail;

    private String profileImage;

    public Member toEntity() {
        return Member.createMemberBuilder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .role(this.role)
                .birthday(this.birthdayYear + this.birthdayMonth + this.birthdayDay)
                .gender(this.gender)
                .mobile(new Mobile(this.phoneNumberFirst, this.phoneNumberMiddle, this.phoneNumberLast))
                .address(new Address(this.zipcode, this.address, this.addressDetail))
                .build();
    }

    //validate
    public void validate() {
        List<ErrorCode> errorCodes = new ArrayList<>();

        if ((this.birthdayYear + this.birthdayMonth + this.birthdayDay).length() != 8) {
            errorCodes.add(new ErrorCode("birthday", "Length.createMemberRequest.birthday", null));
        }

        if ((this.phoneNumberFirst + this.phoneNumberMiddle + this.phoneNumberLast).length() != 11) {
            errorCodes.add(new ErrorCode("phoneNumber", "Length.createMemberRequest.phoneNumber", null));
        }

        if (errorCodes.size() > 0) {
            throw new JsonInvalidRequest(errorCodes);
        }
    }
}
