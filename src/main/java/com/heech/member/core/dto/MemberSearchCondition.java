package com.heech.member.core.dto;

import com.heech.member.common.dto.CommonSearchCondition;
import com.heech.member.core.domain.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSearchCondition extends CommonSearchCondition {

    private Gender searchGender;
    private String searchIsLocked;
    private String searchIsDormancy;

}
