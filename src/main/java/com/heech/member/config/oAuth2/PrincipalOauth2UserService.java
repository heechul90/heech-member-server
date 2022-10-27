package com.heech.member.config.oAuth2;

import com.heech.member.config.auth.PrincipalDetail;
import com.heech.member.config.oAuth2.provider.GoogleUserInfo;
import com.heech.member.config.oAuth2.provider.KakaoUserInfo;
import com.heech.member.config.oAuth2.provider.OAuth2userInfo;
import com.heech.member.core.domain.Member;
import com.heech.member.core.domain.Role;
import com.heech.member.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final PasswordEncoder passwordEncoder;

    private MemberRepository memberRepository;

    /**
     * 소셜 로그인 사이트로부터 받은 userRequest 데이터에 대한 후 처리 함수
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2userInfo oAuth2userInfo = null;

        if( userRequest.getClientRegistration().getRegistrationId().equals("google") ) {

            log.info("구글 로그인 요청");
            oAuth2userInfo = new GoogleUserInfo(oAuth2User.getAttributes());

        }  else if ( userRequest.getClientRegistration().getRegistrationId().equals("kakao") ) {

            log.info("카카오 로그인 요청");
            oAuth2userInfo = new KakaoUserInfo((Map)oAuth2User.getAttributes());

        } else {
            log.info("우리 사이트는 구글, 카카오 로그인만 지원합니다.");
        }

        String provider   = oAuth2userInfo.getProvider();                             // google
        String providerId = oAuth2userInfo.getProviderId();                           // 숫자 ex) 12345566
        String username   = provider + "_" + providerId;                              // google_123456
        String password   = passwordEncoder.encode("겟인데어");
        String email      = oAuth2userInfo.getEmail();
        Role role     = Role.ROLE_USER;

        Member member = memberRepository.findByEmail(email)
                .orElse(null);
        if (member == null) {
            member = Member.createMemberBuilder()
                    .email(oAuth2userInfo.getEmail())
                    .password(password)
                    .name(oAuth2userInfo.getName())
                    .role(role)
                    .build();
            memberRepository.save(member);
        } else {
            log.info("가입되어 있음");
        }

        return new PrincipalDetail(member, oAuth2User.getAttributes());
    }
}
