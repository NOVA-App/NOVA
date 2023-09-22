package com.sehbeomschool.nova.domain.user.service;

import com.sehbeomschool.nova.domain.user.dao.UserRepository;
import com.sehbeomschool.nova.domain.user.domain.User;
import com.sehbeomschool.nova.domain.user.dto.KakaoUserInfoDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.LoginResponseDto;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long createUser(KakaoUserInfoDto user) {
        User saved = userRepository.save(user.toEntity());
        return saved.getId();
    }

    @Override
    public User readUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User readUserBySocialId(Long socialId) {
        return userRepository.findBySocialId(socialId).orElse(null);
    }

    @Override
    public void updateUserProfileImg(Long userId, String profileImg) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return;
        }
        user.updateProfileImg(profileImg);
    }

    @Override
    public void updateUserName(Long userId, String name) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return;
        }
        user.updateName(name);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public boolean isExistUser(Long socialId) {
        Optional<User> bySocialId = userRepository.findBySocialId(socialId);
        return bySocialId.isPresent();
    }

    @Override
    public LoginResponseDto login(Long socialId) {
        User bySocialId = userRepository.findBySocialId(socialId).orElse(null);

        return null;
    }
}
