package com.sehbeomschool.nova.domain.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.sehbeomschool.nova.domain.user.dao.UserRepository;
import com.sehbeomschool.nova.domain.user.domain.User;
import com.sehbeomschool.nova.domain.user.exception.UserExistException;
import com.sehbeomschool.nova.domain.user.exception.UserNotFoundException;
import com.sehbeomschool.nova.global.file.FileStore;
import com.sehbeomschool.nova.global.util.JwtUtil;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FileStore fileStore;

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

    @Test
    void readUser() {
    }

    @Test
    void readUserBySocialId() {
    }

    @Test
    void updateUserProfileImg() {
    }

    @Test
    void updateUserName() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void isExistUser() {
    }

    @Test
    void kakaoLogin() {
    }
}