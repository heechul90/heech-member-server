package com.heech.member.config.auth;

import com.heech.member.core.domain.Member;
import com.heech.member.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


/**
 * 시큐리티 설정에서 .loginProcessingUrl("/login")
 * login 요청이 오면 자동으로 UserDetailsService 타입으로 Ioc 되어 있는 loadUserByUsername 함수가 실행
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username = {}", username);
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "은 존재하지 않는 회원입니다."));
        return new PrincipalDetail(member);
    }

    private User getUser(Member findMember) {
        return new User(
                String.valueOf(findMember.getId()),
                findMember.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(findMember.getRole().name()))
        );
    }
}
