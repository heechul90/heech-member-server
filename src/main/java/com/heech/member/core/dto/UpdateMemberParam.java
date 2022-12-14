package com.heech.member.core.dto;

import com.heech.member.core.domain.Address;
import com.heech.member.core.domain.Gender;
import com.heech.member.core.domain.Mobile;
import com.heech.member.core.domain.Role;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UpdateMemberParam {

    //로그인정보
    private String name;
    private String nickname;

    //권한정보
    private Role role;

    //개인정보
    private Gender gender;
    private String birthday;
    private Mobile mobile;
    private Address address;
    private String profileImage;

}
