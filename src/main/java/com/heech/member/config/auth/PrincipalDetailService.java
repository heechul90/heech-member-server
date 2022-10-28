package com.heech.member.config.auth;

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
        return new PrincipalDetail(
                memberRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException(username + "은 존재하지 않는 회원입니다."))
        );
    }

    //db 에 user 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getRole().name());

        return new User(
                String.valueOf(member.getId()),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }

    private User getUser(Member findMember) {
        return new User(
                String.valueOf(findMember.getId()),
                findMember.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(findMember.getRole().name()))
        );
    }
}
