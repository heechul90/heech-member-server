package com.heech.member.core.repository;

import com.heech.member.core.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 멤버 조회 by email
     */
    Optional<Member> findByEmail(String email);

    /**
     * 멤버 존재 여부
     */
    boolean existsByEmail(String email);


    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);

}
