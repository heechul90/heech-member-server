package com.heech.member;

import com.heech.member.core.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final InitService initService;

    @PostConstruct
    public void init() {

        //멤버
        initService.memberInit();

    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final PasswordEncoder passwordEncoder;
        @PersistenceContext public final EntityManager em;

        private static Member getMember(String email, String password, String name, String nickname, Role role, String birthday, Gender gender, Mobile mobile, Address address) {
            return Member.createMemberBuilder()
                    .email(email)
                    .password(password)
                    .name(name)
                    .nickname(nickname)
                    .role(role)
                    .birthday(birthday)
                    .gender(gender)
                    .mobile(mobile)
                    .address(address)
                    .build();
        }

        public void memberInit() {
            Member springMember = getMember(
                    "spring@mail.com",
                    passwordEncoder.encode("1234"),
                    "스프링",
                    "스플이닉네임",
                    Role.ROLE_USER,
                    "19901009",
                    Gender.M,
                    new Mobile("010", "4250", "4296"),
                    new Address("00000", "Sejong", "101호")
            );
            Member adminMember = getMember(
                    "admin@mail.com",
                    passwordEncoder.encode("1234"),
                    "관리자",
                    "관리자닉네임",
                    Role.ROLE_ADMIN,
                    "20001009",
                    Gender.F,
                    new Mobile("010", "4250", "4296"),
                    new Address("00000", "Sejong", "101호")
            );
            em.persist(springMember);
            em.persist(adminMember);
        }
    }
}
