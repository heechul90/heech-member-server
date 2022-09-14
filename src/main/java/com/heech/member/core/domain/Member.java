package com.heech.member.core.domain;

import javax.persistence.*;

@Entity
@Table(name = "member")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String loginId;
    private String password;
    private String email;
    private String birth;
}
