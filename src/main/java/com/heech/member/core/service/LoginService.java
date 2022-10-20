package com.heech.member.core.service;

import com.heech.member.core.domain.Member;
import com.heech.member.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Member loginMember(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByLoginId(username).stream()
                .filter(member -> member.getLoginId().equals(username))
                .findFirst()
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username + " 존재하지 않는 회원입니다."));
    }

    private UserDetails createUserDetails(Member member) {
        return new User(
                String.valueOf(member.getId()),
                member.getPassword(),
                Collections.singleton((GrantedAuthority) new SimpleGrantedAuthority(member.getRole().toString()))
        );
    }
}
