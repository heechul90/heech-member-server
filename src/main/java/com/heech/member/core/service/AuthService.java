package com.heech.member.core.service;

import com.heech.member.core.domain.Member;
import com.heech.member.core.repository.MemberRepository;
import com.heech.member.jwt.TokenDto;
import com.heech.member.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
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
public class AuthService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder managerBuilder;
    private final TokenProvider tokenProvider;

    /**
     * 회원가입
     */
    @Transactional
    public Member signup(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 회원입니다.");
        }
        return memberRepository.save(member);
    }

    /**
     * 로그인
     */
    public TokenDto login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
        return tokenProvider.generateTokenDto(authentication);
    }
}
