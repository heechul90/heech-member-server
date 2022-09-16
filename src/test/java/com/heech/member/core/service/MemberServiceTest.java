package com.heech.member.core.service;

import com.heech.member.core.domain.Address;
import com.heech.member.core.domain.Member;
import com.heech.member.core.domain.Mobile;
import com.heech.member.core.repository.MemberQueryRepository;
import com.heech.member.core.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    //CREATE_MEMBER
    public static final String LOGIN_ID = "test_loginId";
    public static final String PASSWORD = "test_password";
    public static final String NAME = "test_name";
    public static final String EMAIL = "test_mail@main.com";
    public static final String BIRTHDAY = "19901009";
    public static final Mobile MOBILE = new Mobile("010", "1234", "1234");
    public static final Address ADDRESS = new Address("11111", "seoul", "501");

    //UPDATE_MEMBER

    @InjectMocks private MemberService memberService;

    @Mock private MemberQueryRepository memberQueryRepository;

    @Mock private MemberRepository memberRepository;

    private Member getMember(String loginId, String password, String name, String email, String birthday, Mobile mobile, Address address) {
        return Member.createMemberBuilder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .email(email)
                .birthday(birthday)
                .mobile(mobile)
                .address(address)
                .build();
    }

    @Test
    void findMembers() {
    }

    @Test
    void findMember() {
        Member member = getMember(LOGIN_ID, PASSWORD, NAME, EMAIL, BIRTHDAY, MOBILE, ADDRESS);
    }

    @Test
    void saveMember() {
    }

    @Test
    void updateMember() {
    }

    @Test
    void deleteMember() {
    }
}