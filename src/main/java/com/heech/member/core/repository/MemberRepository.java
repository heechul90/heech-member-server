package com.heech.member.core.repository;

import com.heech.member.core.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /** 멤버 조회 by loginId */
    Optional<Member> findByLoginId(String loginId);

    /** duplication check */
    int countMemberByLoginId(String loginId);

}
