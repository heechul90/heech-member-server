package com.heech.member.core.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    //로그임정보
    private String loginId;
    private String password;
    private String name;
    private String email;

    //개인정보
    private String birthday;
    private String gender;
    private String mobile;
    private String address;
    private String profileImage;

    //잠김
    private boolean isLock;
    private int lockCount;
    private LocalDateTime lastLockedDate;

    //휴먼여부
    private boolean isDormancy;
    private LocalDateTime lastDormancyDate;

}
