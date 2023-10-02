package com.sehbeomschool.nova.domain.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.sehbeomschool.nova.domain.user.dao.UserRepository;
import com.sehbeomschool.nova.domain.user.domain.User;
import com.sehbeomschool.nova.domain.user.dto.KakaoUserInfoDto;
import com.sehbeomschool.nova.domain.user.exception.UserExistException;
import com.sehbeomschool.nova.domain.user.exception.UserNotFoundException;
import com.sehbeomschool.nova.global.util.JwtUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("유저 서비스 단위 테스트")
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Nested
    @DisplayName("유저 생성 테스트")
    class createUser {

        @Test
        @DisplayName("회원 생성 성공")
        void success_createUser() {
            User user = User.builder().id(1L).build();
            given(userRepository.save(user)).willReturn(user);

            Long userId = userService.createUser(user);

            then(userRepository).should().save(user);
            assertThat(userId).isEqualTo(1L);

        }

        @Test
        @DisplayName("이미 존재하는 회원이면 생성 실패")
        void fail_createUser() {

            User user = User.builder().id(1L).socialId(123L).build();
            given(userRepository.findBySocialId(user.getSocialId())).willReturn(Optional.of(user));

            assertThatThrownBy(() -> userService.createUser(user)).isInstanceOf(
                UserExistException.class);

        }
    }

    @Nested
    @DisplayName("유저 조회")
    class readUser {

        @Test
        @DisplayName("존재하는 유저이면")
        void success_readUser() {
            User user = User.builder().id(1L).socialId(123L).build();
            given(userRepository.findById(any(Long.class))).willReturn(Optional.of(user));

            assertThat(userService.readUser(any(Long.class))).isNotNull();
        }

        @Test
        @DisplayName("존재하지 않는 유저이면")
        void fail_readUser() {
            given(userRepository.findById(any(Long.class))).willReturn(Optional.empty());

            assertThatThrownBy(() -> userService.readUser(any(Long.class))).isInstanceOf(
                UserNotFoundException.class
            );
        }

    }

    @Nested
    @DisplayName("유저 조회 by 소셜아이디")
    class readUserBySocialId {

        @Test
        @DisplayName("존재하는 유저이면")
        void success_readUserBySocialId() {
            User user = User.builder().id(1L).socialId(123L).build();
            given(userRepository.findBySocialId(any(Long.class))).willReturn(Optional.of(user));

            User findUser = userService.readUserBySocialId(any(Long.class));

            assertThat(findUser).isNotNull();
            assertThat(findUser.getId()).isEqualTo(1L);
        }

        @Test
        @DisplayName("존재하지 않는 유저이면")
        void fail_readUserBySocialId() {
            given(userRepository.findBySocialId(any(Long.class))).willReturn(Optional.empty());

            assertThatThrownBy(() -> userService.readUserBySocialId(any(Long.class))).isInstanceOf(
                UserNotFoundException.class
            );
        }

    }

    @Nested
    @DisplayName("유저 카카오 로그인")
    class kakaoLogin {

        private Map<String, Object> info;
        private KakaoUserInfoDto kakaoUserInfoDto;

        @BeforeEach
        void setUp() {
            info = new HashMap<>();
            info.put("id", 123);

            Map<String, Object> properties = new HashMap<>();
            properties.put("nickname", "name");
            properties.put("profile_image", "https://example.com/profile.jpg");

            info.put("properties", properties);
            kakaoUserInfoDto = KakaoUserInfoDto.builder().info(info).build();
        }

        @Nested
        @DisplayName("가입이 안된 회원이면")
        class not_exist_user {

            @Test
            @DisplayName("회원가입 진행")
            void create_user() {

                User user = User.builder().id(1L).build();
                given(userRepository.findBySocialId(any(Long.class))).willReturn(Optional.empty());
                given(userRepository.save(any())).willReturn(user);

                assertThatThrownBy(() -> userService.kakaoLogin(kakaoUserInfoDto)).isInstanceOf(
                    UserNotFoundException.class
                );
                then(userRepository).should().save(any());
            }
        }

        @Nested
        @DisplayName("가입이 된 회원이면")
        class exist_user {

            @Test
            @DisplayName("유저정보 불러오고")
            void get_userInfo() {
                User user = User.builder().id(1L).build();
                given(userRepository.findBySocialId(any(Long.class))).willReturn(Optional.of(user));

                userService.kakaoLogin(kakaoUserInfoDto);

                then(userRepository).should(times(2)).findBySocialId(any(Long.class));
            }

            @Test
            @DisplayName("토큰 반환")
            void fail_readUserBySocialId() {
                User user = User.builder().id(1L).build();
                given(userRepository.findBySocialId(any(Long.class))).willReturn(Optional.of(user));

                userService.kakaoLogin(kakaoUserInfoDto);

                then(jwtUtil).should().createJwtToken(user.getId());
                then(jwtUtil).should().createRefreshToken(user.getId());
            }
        }

    }

}