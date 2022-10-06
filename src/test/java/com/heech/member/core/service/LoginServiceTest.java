package com.heech.member.core.service;

import com.heech.member.core.domain.*;
import com.heech.member.core.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    //CREATE_MEMBER
    public static final String LOGIN_ID = "test_loginId";
    public static final String PASSWORD = "test_password";
    public static final String NAME = "test_name";
    public static final String EMAIL = "test_mail@main.com";
    public static final Role ROLE = Role.ROLE_USER;
    public static final String BIRTHDAY = "19901009";
    public static final Gender GENDER = Gender.M;
    public static final Mobile MOBILE = new Mobile("010", "1234", "1234");
    public static final Address ADDRESS = new Address("11111", "seoul", "501");

    @InjectMocks LoginService loginService;

    @Mock MemberRepository memberRepository;

    private Member getMember(String loginId, String password, String name, String email, Role role, String birthday, Gender gender, Mobile mobile, Address address) {
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

    @Test
    @DisplayName("로그인 회원 조회")
    void loginMember() {
        //given
        Member member = getMember(LOGIN_ID, PASSWORD, NAME, EMAIL, ROLE, BIRTHDAY, GENDER, MOBILE, ADDRESS);
        given(memberRepository.findByLoginId(any(String.class))).willReturn(Optional.ofNullable(member));

        //when
        Member findMember = loginService.loginMember(LOGIN_ID, PASSWORD);

        //then
        assertThat(findMember.getLoginId()).isEqualTo(LOGIN_ID);
        assertThat(findMember.getPassword()).isEqualTo(PASSWORD);
        assertThat(findMember.getName()).isEqualTo(NAME);
        assertThat(findMember.getEmail()).isEqualTo(EMAIL);
        assertThat(findMember.getRole()).isEqualTo(ROLE);
        assertThat(findMember.getBirthday()).isEqualTo(BIRTHDAY);
        assertThat(findMember.getGender()).isEqualTo(GENDER);
        assertThat(findMember.getMobile()).isEqualTo(MOBILE);
        assertThat(findMember.getAddress()).isEqualTo(ADDRESS);
    }

    @Test
    @DisplayName("로그인 회원 조회_실패")
    void loginMember_exception() {
        //given
        given(memberRepository.findByLoginId(any())).willReturn(Optional.ofNullable(null));

        //when
        Member findMember = loginService.loginMember(LOGIN_ID, PASSWORD);

        //then
        assertThat(findMember).isNull();
    }
}