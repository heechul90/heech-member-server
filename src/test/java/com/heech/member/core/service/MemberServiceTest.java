package com.heech.member.core.service;

import com.heech.member.core.domain.*;
import com.heech.member.core.dto.MemberSearchCondition;
import com.heech.member.core.dto.SearchCondition;
import com.heech.member.core.dto.UpdateMemberParam;
import com.heech.member.core.repository.MemberQueryRepository;
import com.heech.member.core.repository.MemberRepository;
import com.heech.member.common.exception.EntityNotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    //CREATE_MEMBER
    private static final String USERNAME = "test_username";
    public static final String PASSWORD = "test_password";
    public static final String EMAIL = "test_mail@main.com";
    public static final String NAME = "test_name";
    public static final String NICKNAME = "test_nickname";
    public static final Role ROLE = Role.ROLE_USER;
    public static final String BIRTHDAY = "19901009";
    public static final Gender GENDER = Gender.M;
    public static final Mobile MOBILE = new Mobile("010", "1234", "1234");
    public static final Address ADDRESS = new Address("11111", "seoul", "501");

    //UPDATE_MEMBER
    public static final String UPDATE_NAME = "update_name";
    public static final String UPDATE_NICKNAME = "update_nickname";
    public static final Role UPDATE_ROLE = Role.ROLE_ADMIN;
    public static final String UPDATE_BIRTHDAY = "19921009";
    public static final Gender UPDATE_GENDER = Gender.F;
    public static final Mobile UPDATE_MOBILE = new Mobile("010", "6474", "4296");
    public static final Address UPDATE_ADDRESS = new Address("22222", "세종시", "601호");
    public static final String UPDATE_PROFILE_IMAGE = "update_profileImage";

    public static final String ENTITY_NAME = "member";
    public static final long NOT_FOUND_ENTITY_ID = 0L;

    //ERORR MESSAGE
    public static final String HAS_MESSAGE_STARTING_WITH = "존재하지 않은 " + ENTITY_NAME;
    public static final String HAS_MEASSAGE_ENDING_WITH = "id = " + NOT_FOUND_ENTITY_ID;

    @InjectMocks MemberService memberService;

    @Mock MemberQueryRepository memberQueryRepository;

    @Mock MemberRepository memberRepository;

    private Member getMember(String username, String password, String email,  String name, String nickname, Role role, String birthday, Gender gender, Mobile mobile, Address address) {
        return Member.createMemberBuilder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .nickname(nickname)
                .role(role)
                .birthday(birthday)
                .gender(gender)
                .mobile(mobile)
                .address(address)
                .build();
    }

    @Test
    @DisplayName("멤버 목록 조회")
    void findMembers() {
        //given
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            members.add(getMember(USERNAME, PASSWORD, EMAIL, NAME + i, NICKNAME, ROLE, BIRTHDAY, GENDER, MOBILE, ADDRESS));
        }
        given(memberQueryRepository.findMembers(any(), any())).willReturn(new PageImpl(members));

        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setSearchCondition(SearchCondition.NAME);
        condition.setSearchKeyword("test");
        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        Page<Member> content = memberService.findMembers(condition, pageRequest);

        //then
        assertThat(content.getTotalElements()).isEqualTo(15);
        assertThat(content.getContent().size()).isEqualTo(15);

        //verify
    }

    @Test
    @DisplayName("멤버 단건 조회")
    void findMember() {
        //given
        Member member = getMember(USERNAME, PASSWORD, EMAIL, NAME, NICKNAME, ROLE, BIRTHDAY, GENDER, MOBILE, ADDRESS);
        given(memberRepository.findById(any(Long.class))).willReturn(Optional.ofNullable(member));

        //when
        Member findMember = memberService.findMember(any(Long.class));

        //then
        assertThat(findMember.getEmail()).isEqualTo(EMAIL);
        assertThat(findMember.getPassword()).isEqualTo(PASSWORD);
        assertThat(findMember.getName()).isEqualTo(NAME);
        assertThat(findMember.getRole()).isEqualTo(ROLE);
        assertThat(findMember.getBirthday()).isEqualTo(BIRTHDAY);
        assertThat(findMember.getGender()).isEqualTo(GENDER);
        assertThat(findMember.getMobile()).isEqualTo(MOBILE);
        assertThat(findMember.getAddress()).isEqualTo(ADDRESS);

        //verify
        verify(memberRepository, times(1)).findById(any(Long.class));
    }

    @Test
    @DisplayName("멤버 단건 조회_예외 발생")
    void findMember_exception() {
        //expected
        assertThatThrownBy(() -> memberService.findMember(NOT_FOUND_ENTITY_ID))
                .isInstanceOf(EntityNotFound.class)
                .hasMessageStartingWith(HAS_MESSAGE_STARTING_WITH)
                .hasMessageEndingWith(HAS_MEASSAGE_ENDING_WITH);

        //verify
        verify(memberRepository, times(1)).findById(any(Long.class));
    }

    @Test
    @DisplayName("멤버 저장")
    void saveMember() {
        //given
        Member member = getMember(USERNAME, PASSWORD, EMAIL, NAME, NICKNAME, ROLE, BIRTHDAY, GENDER, MOBILE, ADDRESS);
        given(memberRepository.save(any(Member.class))).willReturn(member);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        assertThat(savedMember.getEmail()).isEqualTo(EMAIL);
        assertThat(savedMember.getPassword()).isEqualTo(PASSWORD);
        assertThat(savedMember.getName()).isEqualTo(NAME);
        assertThat(savedMember.getRole()).isEqualTo(ROLE);
        assertThat(savedMember.getBirthday()).isEqualTo(BIRTHDAY);
        assertThat(savedMember.getGender()).isEqualTo(GENDER);
        assertThat(savedMember.getMobile()).isEqualTo(MOBILE);
        assertThat(savedMember.getAddress()).isEqualTo(ADDRESS);

        //verify
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    @DisplayName("멤버 수정")
    void updateMember() {
        //given
        Member member = getMember(USERNAME, PASSWORD, EMAIL, NAME, NICKNAME, ROLE, BIRTHDAY, GENDER, MOBILE, ADDRESS);
        given(memberRepository.findById(any(Long.class))).willReturn(Optional.ofNullable(member));

        UpdateMemberParam param = UpdateMemberParam.builder()
                .name(UPDATE_NAME)
                .role(UPDATE_ROLE)
                .birthday(UPDATE_BIRTHDAY)
                .gender(UPDATE_GENDER)
                .mobile(UPDATE_MOBILE)
                .address(UPDATE_ADDRESS)
                .profileImage(UPDATE_PROFILE_IMAGE)
                .build();

        //when
        memberService.updateMember(any(Long.class), param);

        //then
        assertThat(member.getEmail()).isEqualTo(EMAIL);
        assertThat(member.getPassword()).isEqualTo(PASSWORD);
        assertThat(member.getName()).isEqualTo(UPDATE_NAME);
        assertThat(member.getRole()).isEqualTo(UPDATE_ROLE);
        assertThat(member.getBirthday()).isEqualTo(UPDATE_BIRTHDAY);
        assertThat(member.getGender()).isEqualTo(UPDATE_GENDER);
        assertThat(member.getAddress()).isEqualTo(UPDATE_ADDRESS);

        //verify
        verify(memberRepository, times(1)).findById(any(Long.class));
    }

    @Test
    @DisplayName("멤버 수정_예외 발생")
    void updateMember_exception() {
        //expected
        assertThatThrownBy(() -> memberService.updateMember(NOT_FOUND_ENTITY_ID, any()))
                .isInstanceOf(EntityNotFound.class)
                .hasMessageStartingWith(HAS_MESSAGE_STARTING_WITH)
                .hasMessageEndingWith(HAS_MEASSAGE_ENDING_WITH);

        //verify
        verify(memberRepository, times(1)).findById(any(Long.class));
    }

    @Test
    @DisplayName("멤버 삭제")
    void deleteMember() {
        //given
        Member member = getMember(USERNAME, PASSWORD, EMAIL, NAME, NICKNAME, ROLE, BIRTHDAY, GENDER, MOBILE, ADDRESS);
        given(memberRepository.findById(any(Long.class))).willReturn(Optional.ofNullable(member));

        //when
        memberService.deleteMember(any(Long.class));

        //then
        assertThatThrownBy(() -> memberService.deleteMember(member.getId()))
                .isInstanceOf(EntityNotFound.class)
                .hasMessageStartingWith(HAS_MESSAGE_STARTING_WITH)
                .hasMessageEndingWith("id = " + member.getId());

        //verify
        verify(memberRepository, times(1)).delete(any(Member.class));
    }
}