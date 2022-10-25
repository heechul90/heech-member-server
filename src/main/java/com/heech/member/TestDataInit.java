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

        private static Member getMember(String loginId, String password, String name, String email, Role role, String birthday, Gender gender, Mobile mobile, Address address) {
            return Member.createMemberBuilder()
                    .loginId(loginId)
                    .password(password)
                    .name(name)
                    .email(email)
                    .role(role)
                    .birthday(birthday)
                    .gender(gender)
                    .mobile(mobile)
                    .address(address)
                    .build();
        }

        public void memberInit() {
            Member springMember = getMember("spring", passwordEncoder.encode("1234"), "스프링", "spring@spring.com", Role.ROLE_USER, "19901009", Gender.M, new Mobile("010", "4250", "4296"), new Address("00000", "Sejong", "101호"));
            Member adminMember = getMember("admin", passwordEncoder.encode("1234"), "관리자", "admin@spring.com", Role.ROLE_ADMIN, "20001009", Gender.F, new Mobile("010", "4250", "4296"), new Address("00000", "Sejong", "101호"));
            em.persist(springMember);
            em.persist(adminMember);
        }
    }
}
