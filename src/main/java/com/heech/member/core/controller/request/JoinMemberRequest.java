package com.heech.member.core.controller.request;

import com.heech.member.common.json.JsonError;
import com.heech.member.core.domain.Gender;
import com.heech.member.core.domain.Member;
import com.heech.member.core.domain.Role;
import com.heech.member.exception.JsonInvalidRequest;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class JoinMemberRequest {

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

    public Member toEntity() {
        return Member.createMemberBuilder()
                .loginId(this.loginId)
                .password(this.password)
                .name(this.name)
                .email(this.email)
                .role(Role.ROLE_USER)
                .birthday(null)
                .gender(Gender.M)
                .mobile(null)
                .address(null)
                .profileImage(null)
                .build();
    }

    //validate
    public void validate() {
        List<JsonError> errors = new ArrayList<>();


        if (errors.size() > 0) {
            throw new JsonInvalidRequest(errors);
        }

    }
}
