package com.heech.member.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ROLE_USER("user", "일반유저"),
    ROLE_ADMIN("admin", "관리자");

    private final String code;
    private final String name;
}
