package com.sehbeomschool.nova.domain.user.dto;

import com.sehbeomschool.nova.domain.user.domain.User;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KakaoUserInfoDto {

    private Long id;
    private String name;
    private String profileImg;

    @Builder
    public KakaoUserInfoDto(Map<String, Object> info) {
        this.id = ((Integer) info.get("id")).longValue();
        this.name = String.valueOf(((Map<?, ?>) info.get("properties")).get("nickname"));
        this.profileImg = String.valueOf(((Map<?, ?>) info.get("properties")).get("profile_image"));
    }

    public User toEntity() {
        return User.builder().socialId(id).name(name).profileImg(profileImg).build();
    }
}
