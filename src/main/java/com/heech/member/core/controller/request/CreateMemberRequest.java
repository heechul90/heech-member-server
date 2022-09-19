package com.heech.member.core.controller.request;

import com.heech.member.common.json.JsonError;
import com.heech.member.core.domain.*;
import com.heech.member.exception.JsonInvalidRequest;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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
    @NotBlank(message = "아이디를 입력하세요.")
    @Length(max = 80)
    private String loginId;
    @NotBlank(message = "비밀번호를 입력하세요")
    @Length(max = 60)
    private String password;
    @NotBlank(message = "이름을 입력하세요.")
    @Length(max = 30)
    private String name;
    @NotBlank(message = "이메일을 입력하세요.")
    @Email
    @Length(max = 60)
    private String email;

    //권한정보
    @NotNull
    private Role role;

    //개인정보
    @NotEmpty(message = "생년월일 연도를 선택하세요.")
    @Length(min = 4, max = 4)
    private String birthdayYear;
    @NotEmpty(message = "생년월일 월을 선택하세요.")
    @Length(min = 2, max = 2)
    private String birthdayMonth;
    @Length(min = 2, max = 2)
    @NotEmpty(message = "생년월일 일을 입력하세요.")
    private String birthdayDay;

    @NotNull(message = "성별을 선택하세요.")
    private Gender gender;

    @NotEmpty(message = "휴대폰번호 첫번째자리를 선택하세요.")
    @Length(min = 3, max = 3)
    private String phoneNumberFirst;
    @NotEmpty(message = "휴대폰번호 중간자리를 입력하세요.")
    @Length(min = 4, max = 4)
    private String phoneNumberMiddle;
    @NotEmpty(message = "휴대폰번호 마지막자리를 입력하세요.")
    @Length(min = 4, max = 4)
    private String phoneNumberLast;

    @NotEmpty(message = "우편번호를 입력하세요.")
    @Length(min = 5, max = 5)
    private String zipcode;
    @NotEmpty(message = "주소를 입력하세요.")
    @Length(max = 255)
    private String address;
    @NotEmpty(message = "상세주소를 입력하세요.")
    @Length(max = 255)
    private String addressDetail;

    @Length(max = 255)
    private String profileImage;

    public Member toEntity() {
        return Member.createMemberBuilder()
                .loginId(this.loginId)
                .password(this.password)
                .name(this.name)
                .email(this.email)
                .role(this.role)
                .birthday(this.birthdayYear + this.birthdayMonth + this.birthdayDay)
                .gender(this.gender)
                .mobile(new Mobile(this.phoneNumberFirst, this.phoneNumberMiddle, this.phoneNumberLast))
                .address(new Address(this.zipcode, this.address, this.addressDetail))
                .profileImage(this.profileImage)
                .build();
    }

    //validate
    public void validate() {
        List<JsonError> errors = new ArrayList<>();

        if ((this.birthdayYear + this.birthdayMonth + this.birthdayDay).length() != 8) {
            errors.add(new JsonError("birthday", "생년월일을 확인해주세요."));
        }

        if ((this.phoneNumberFirst + this.phoneNumberMiddle + this.phoneNumberLast).length() != 11) {
            errors.add(new JsonError("phoneNumber", "휴대폰번호를 확인해주세요."));
        }

        if (errors.size() > 0) {
            throw new JsonInvalidRequest(errors);
        }
    }

}
