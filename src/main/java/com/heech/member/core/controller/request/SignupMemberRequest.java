package com.heech.member.core.controller.request;

import com.heech.member.common.json.ErrorCode;
import com.heech.member.core.domain.Gender;
import com.heech.member.core.domain.Member;
import com.heech.member.core.domain.Role;
import com.heech.member.common.exception.JsonInvalidRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SignupMemberRequest {

    //로그인정보
    @NotBlank(message = "이메일을 입력하세요.")
    //@Email
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

    @NotBlank(message = "이름을 입력하세요.")
    private String name;


    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.createMemberBuilder()
                .email(this.email)
                .password(passwordEncoder.encode(this.password))
                .name(this.name)
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
        List<ErrorCode> errorCodes = new ArrayList<>();


        if (errorCodes.size() > 0) {
            throw new JsonInvalidRequest(errorCodes);
        }
    }
}
