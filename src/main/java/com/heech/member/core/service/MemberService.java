package com.heech.member.core.service;

import com.heech.member.core.domain.Member;
import com.heech.member.core.dto.MemberSearchCondition;
import com.heech.member.core.dto.UpdateMemberParam;
import com.heech.member.core.repository.MemberQueryRepository;
import com.heech.member.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberQueryRepository memberQueryRepository;
    private final MemberRepository memberRepository;

    /**
     * 회원 목록 조회
     */
    public Page<Member> findMembers(MemberSearchCondition condition, Pageable pageable) {
        return memberQueryRepository.findMembers(condition, pageable);
    }

    /**
     * 회원 단건 조회
     */
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException());
    }

    /**
     * 회원 저장
     */
    @Transactional
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    /**
     * 회원 수정
     */
    @Transactional
    public void updateMember(Long memberId, UpdateMemberParam param) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException());
        findMember.updateMember(param);
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void deleteMember(Long memberId) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException());
        memberRepository.delete(findMember);
    }
}
