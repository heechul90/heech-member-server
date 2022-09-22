package com.heech.member.core.service;

import com.heech.member.core.domain.Member;
import com.heech.member.core.repository.MemberRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

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
}
