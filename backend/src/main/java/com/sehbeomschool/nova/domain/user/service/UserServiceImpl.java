package com.sehbeomschool.nova.domain.user.service;

import com.sehbeomschool.nova.domain.user.dao.UserRepository;
import com.sehbeomschool.nova.domain.user.domain.User;
import com.sehbeomschool.nova.domain.user.dto.KakaoUserInfoDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.LoginResponseDto;
import com.sehbeomschool.nova.global.file.FileStore;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FileStore fileStore;

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
    public String updateUserProfileImg(Long userId, MultipartFile profileImg) {
        String filePath = fileStore.storeFile(profileImg);
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }
        fileStore.deleteFile(user.getProfileImg());
        user.updateProfileImg(filePath);
        return filePath;
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
        User user = userRepository.findById(userId).orElseThrow();
        user.signOut();
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
