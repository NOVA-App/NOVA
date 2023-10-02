package com.sehbeomschool.nova.domain.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.sehbeomschool.nova.domain.user.dao.UserRepository;
import com.sehbeomschool.nova.domain.user.domain.User;
import com.sehbeomschool.nova.domain.user.exception.UserExistException;
import com.sehbeomschool.nova.global.file.FileStore;
import com.sehbeomschool.nova.global.util.JwtUtil;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
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

    @Test
    @DisplayName("회원 생성 성공 테스트")
    void success_createUser() {
        User user = User.builder().id(1L).build();
        given(userRepository.save(user)).willReturn(user);

        Long userId = userService.createUser(user);

        then(userRepository).should().save(user);
        assertThat(userId).isEqualTo(1L);

    }

    @Test
    @DisplayName("회원 생성 실패 테스트")
    void fail_createUser() {

        User user = User.builder().id(1L).socialId(123L).build();
        given(userRepository.findBySocialId(user.getSocialId())).willReturn(Optional.of(user));

        assertThatThrownBy(() -> userService.createUser(user)).isInstanceOf(
            UserExistException.class);

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