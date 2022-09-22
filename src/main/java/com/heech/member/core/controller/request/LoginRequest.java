package com.heech.member.core.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginRequest {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;
}
