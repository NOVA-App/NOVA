package com.sehbeomschool.nova.domain.user.service;

import static com.sehbeomschool.nova.domain.user.constant.UserExceptionMessage.NOT_EXIST_USER;

import com.sehbeomschool.nova.domain.user.dao.UserRepository;
import com.sehbeomschool.nova.domain.user.domain.User;
import com.sehbeomschool.nova.domain.user.dto.KakaoUserInfoDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.FileUploadResponseDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.TokenResponseDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.UserInfoResponseDto;
import com.sehbeomschool.nova.domain.user.exception.UserNotFoundException;
import com.sehbeomschool.nova.global.file.FileStore;
import com.sehbeomschool.nova.global.util.JwtUtil;
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
    private final JwtUtil jwtUtil;

    @Override
    public Long createUser(KakaoUserInfoDto user) {
        User saved = userRepository.save(user.toEntity());
        return saved.getId();
    }

    @Override
    public UserInfoResponseDto readUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(NOT_EXIST_USER.getMessage()));
        return UserInfoResponseDto.builder().user(user).build();
    }

    @Override
    public User readUserBySocialId(Long socialId) {
        return userRepository.findBySocialId(socialId).orElseThrow(
            () -> new UserNotFoundException(NOT_EXIST_USER.getMessage()));
    }

    @Override
    public FileUploadResponseDto updateUserProfileImg(Long userId, MultipartFile profileImg) {
        String filePath = fileStore.storeFile(profileImg);
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(NOT_EXIST_USER.getMessage()));

        fileStore.deleteFile(user.getProfileImg());
        user.updateProfileImg(filePath);
        return FileUploadResponseDto.builder().filePath(filePath).build();
    }

    @Override
    public void updateUserName(Long userId, String name) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(NOT_EXIST_USER.getMessage()));

        user.updateName(name);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(NOT_EXIST_USER.getMessage()));
        user.signOut();
    }

    @Override
    public boolean isExistUser(Long socialId) {
        Optional<User> bySocialId = userRepository.findBySocialId(socialId);
        return bySocialId.isPresent();
    }

    @Override
    public TokenResponseDto kakaoLogin(KakaoUserInfoDto kakaoUserInfoDto) {
        if (!isExistUser(kakaoUserInfoDto.getId())) {
            createUser(kakaoUserInfoDto);
        }

        User user = readUserBySocialId(kakaoUserInfoDto.getId());

        String accessToken = jwtUtil.createJwtToken(user.getId());
        String refreshToken = jwtUtil.createRefreshToken(user.getId());

        return TokenResponseDto.builder().accessToken(accessToken).refreshToken(refreshToken)
            .build();
    }
}
