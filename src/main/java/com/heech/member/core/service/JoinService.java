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
public class JoinService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Member joinMember(Member member) {
        int countMember = memberRepository.countMemberByLoginId(member.getLoginId());
        if (countMember > 0) {
            throw new DuplicateRequestException("이미 존재하는 longinId 입니다.");
        }

        return memberRepository.save(member);
    }
}
