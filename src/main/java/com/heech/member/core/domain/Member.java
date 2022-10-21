package com.heech.member.core.domain;

import com.heech.member.common.entity.BaseTimeEntity;
import com.heech.member.core.dto.UpdateMemberParam;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String loginId;
    @Column(length = 60, nullable = false)
    private String password;
    @Column(length = 30, nullable = false)
    private String name;
    @Column(length = 60)
    private String email;

    //권한정보
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Role role;

    //개인정보
    @Column(length = 8)
    private String birthday;
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Gender gender;
    @Embedded
    private Mobile mobile;
    @Embedded
    private Address address;
    private String profileImage;

    //잠김설정
    private boolean isLocked;
    private int lockedCount;
    private LocalDateTime lastLockedDate;

    //휴먼계정설정
    private boolean isDormancy;
    private LocalDateTime lastDormancyDate;

    //로그정보
    @Column(updatable = false)
    private LocalDateTime signupDate;
    private LocalDateTime signinDate;

    //===생성===//
    /** 회원 생성 */
    @Builder(builderClassName = "createMemberBuilder", builderMethodName = "createMemberBuilder")
    public Member(String loginId, String password, String name, String email, Role role, String birthday, Gender gender, Mobile mobile, Address address, String profileImage) {
        Assert.hasText(name, "name은 필수값입니다.");
        Assert.hasText(loginId, "loginId는 필수값입니다.");
        Assert.hasText(password, "password는 필수값입니다.");
        Assert.hasText(email, "email은 필수값입니다.");

        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.birthday = birthday;
        this.gender = gender;
        this.mobile = mobile;
        this.address = address;
        this.profileImage = profileImage;
        this.isLocked = false;
        this.lockedCount = 0;
        this.lastLockedDate = null;
        this.isDormancy = false;
        this.lastDormancyDate = null;
        this.signupDate = LocalDateTime.now();
        this.signinDate = LocalDateTime.now();
    }

    /** 회원 수정 */
    public void updateMember(UpdateMemberParam param) {
        this.name = param.getName();
        this.email = param.getEmail();
        this.role = param.getRole();
        this.birthday = hasText(param.getBirthday()) ? param.getBirthday() : null;
        this.gender = param.getGender() != null ? param.getGender() : null;
        this.mobile = param.getMobile() != null ? param.getMobile() : null;
        this.address = param.getAddress() != null ? param.getAddress() : null;
        this.profileImage = hasText(param.getProfileImage()) ? param.getProfileImage() : null;
    }

    //===변경===//
    public void changePassword(String password) {
        this.password = password;
    }
}
