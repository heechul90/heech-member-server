package com.heech.member.core.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

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

    //개인정보
    @Column(length = 8)
    private String birthday;
    @Enumerated(EnumType.STRING)
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
    public Member(String loginId, String password, String name, String email, String birthday, Gender gender, Mobile mobile, Address address, String profileImage) {
        Assert.hasText(name, "name은 필수값입니다.");
        Assert.hasText(loginId, "loginId는 필수값입니다.");
        Assert.hasText(password, "password는 필수값입니다.");
        Assert.hasText(email, "email은 필수값입니다.");

        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
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

    //===변경===//
    public void changePassword(String password) {
        this.password = password;
    }
}
