package com.study.UMC10.global.security.service;

import com.study.UMC10.domain.user.entity.User;
import com.study.UMC10.domain.user.entity.UserLogin;
import com.study.UMC10.domain.user.enums.LoginType;
import com.study.UMC10.domain.user.enums.UserStatus;
import com.study.UMC10.domain.user.repository.UserLoginRepository;
import com.study.UMC10.domain.user.repository.UserRepository;
import com.study.UMC10.global.security.CustomOAuth2User;
import com.study.UMC10.global.security.dto.KakaoDTO;
import com.study.UMC10.global.security.dto.OAuthDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final UserLoginRepository userLoginRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId().toUpperCase();
        LoginType loginType = LoginType.valueOf(registrationId);

        OAuthDTO oAuthDTO;
        if (loginType == LoginType.KAKAO) {
            Map<String, Object> attributes = oAuth2User.getAttribute("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) attributes.get("profile");

            String socialId = String.valueOf(oAuth2User.getAttribute("id"));
            String email = attributes.get("email").toString();
            String name = profile.get("nickname").toString();

            oAuthDTO = new KakaoDTO(socialId, email, name);
        } else {
            throw new OAuth2AuthenticationException("지원하지 않는 소셜 로그인입니다.");
        }

        UserLogin userLogin = userLoginRepository.findByLoginTypeAndSocialId(oAuthDTO.getLoginType(), oAuthDTO.getSocialId())
                .orElse(null);

        User user;
        if (userLogin == null) {
            user = userRepository.findByEmail(oAuthDTO.getEmail())
                    .orElseGet(() -> {
                        User newUser = User.builder()
                                .email(oAuthDTO.getEmail())
                                .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                                .name(oAuthDTO.getName())
                                .status(UserStatus.ACTIVE)
                                .totalPoint(0)
                                .finMission(0)
                                .build();
                        return userRepository.save(newUser);
                    });

            UserLogin newUserLogin = UserLogin.builder()
                    .loginType(oAuthDTO.getLoginType())
                    .socialId(oAuthDTO.getSocialId())
                    .user(user)
                    .build();
            userLoginRepository.save(newUserLogin);
        } else {
            user = userLogin.getUser();
        }

        return new CustomOAuth2User(user, oAuth2User.getAttributes());
    }
}