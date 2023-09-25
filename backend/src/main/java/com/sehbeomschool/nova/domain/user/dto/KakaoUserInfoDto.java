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
    public KakaoUserInfoDto(Map info){
        this.id = (Long) info.get("id");
        this.name = String.valueOf(((Map)info.get("properties")).get("nickname"));
        this.profileImg = String.valueOf(info.get("profile_image_url"));
    }

    public User toEntity(){
        return User.builder().socialId(id).name(name).profileImg(profileImg).build();
    }
}
