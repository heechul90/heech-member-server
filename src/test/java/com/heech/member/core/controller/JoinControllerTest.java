package com.heech.member.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heech.member.core.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(JoinController.class)
class JoinControllerTest {

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

    @Autowired private MockMvc mockMvc;

    @MockBean private JoinService joinService;

    @Autowired private ObjectMapper objectMapper;

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
    void joinMember() {
        //given
        Member member = getMember(LOGIN_ID, PASSWORD, NAME, EMAIL, ROLE, BIRTHDAY, GENDER, MOBILE, ADDRESS);
        given(joinService.joinMember(any(Member.class))).willReturn(member);

        //when
        Member joinedMember = joinService.joinMember(any(Member.class));

        //then
        //assertThat(joinedMember.getLoginId()).isEqualTo(LOGIN_ID);
    }
}