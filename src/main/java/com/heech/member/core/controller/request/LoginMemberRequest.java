package com.heech.member.core.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginMemberRequest {

    @NotBlank
    //@Email
    private String email;

    @NotBlank
    private String password;
}
