package com.heech.member.core.service;

import com.heech.member.core.repository.MemberQueryRepository;
import com.heech.member.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 회원 단건 조회
     */

    /**
     * 회원 저장
     */

    /**
     * 회원 수정
     */

    /**
     * 회원 삭제
     */


}
