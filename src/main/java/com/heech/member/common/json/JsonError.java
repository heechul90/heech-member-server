package com.heech.member.common.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JsonError {

    private String fieldName;
    private String errorMessage;

}
