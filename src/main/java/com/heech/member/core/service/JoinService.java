package com.heech.member.core.service;

import com.heech.member.core.domain.Member;
import com.heech.member.core.repository.MemberRepository;
import com.heech.member.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder managerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    /**
     * 회원가입
     */
    @Transactional
    public Member joinMember(Member member) {
        if (memberRepository.existsByLoginId(member.getLoginId())) {
            throw new RuntimeException("이미 가입되어 있는 회원입니다.");
        }

        member.changePassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }



}
