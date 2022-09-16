package com.heech.member.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    F("Female", "여자"),
    M("Male", "남자"),
    O("Other", "그 외");

    private final String code;
    private final String name;
}
