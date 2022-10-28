package com.heech.member.core.domain;

import com.heech.member.common.entity.BaseTimeEntity;
import com.heech.member.core.dto.UpdateMemberParam;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

import static org.springframework.util.StringUtils.*;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    //로그임정보
    @Column(length = 80, unique = true, nullable = false, updatable = false)
    private String username;
    @Column(length = 60, nullable = false)
    private String password;

    //개인정보1
    @Column(length = 80, unique = true, nullable = false, updatable = false)
    private String email;
    @Column(length = 30, nullable = false)
    private String name;
    @Column(length = 30)
    private String nickname;

    //OAuth2 정보
    private String provider;
    private String providerId;

    //권한정보
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Role role;

    //개인정보2
    @Column(length = 8)
    private String birthday;
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Gender gender;
    @Embedded
    private Mobile mobile;
    @Embedded
    private Address address;

    //===생성===//
    /** 회원 생성 */
    @Builder(builderClassName = "createMemberBuilder", builderMethodName = "createMemberBuilder")
    public Member(String username, String password, String email, String name, String nickname, String provider, String providerId, Role role, String birthday, Gender gender, Mobile mobile, Address address) {
        Assert.hasText(username, "username is required.");
        Assert.hasText(password, "password is required.");
        Assert.hasText(email, "email is required.");
        Assert.hasText(name, "name is required.");

        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.provider = provider;
        this.providerId = providerId;
        this.role = role;
        this.birthday = birthday;
        this.gender = gender;
        this.mobile = mobile;
        this.address = address;
    }

    /** 회원 수정 */
    public void updateMember(UpdateMemberParam param) {
        this.name = param.getName();
        this.role = param.getRole();
        this.birthday = hasText(param.getBirthday()) ? param.getBirthday() : null;
        this.gender = param.getGender() != null ? param.getGender() : null;
        this.mobile = param.getMobile() != null ? param.getMobile() : null;
        this.address = param.getAddress() != null ? param.getAddress() : null;
    }

    //===변경===//
    public void changePassword(String password) {
        this.password = password;
    }
}
