package com.sehbeomschool.nova.domain.user.domain;

import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long socialId;
    private String name;
    private String profileImg;

    @ColumnDefault("false")
    private boolean isDeleted;

    @Builder
    public User(Long id, Long socialId, String name, String profileImg) {
        this.id = id;
        this.socialId = socialId;
        this.name = name;
        this.profileImg = profileImg;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public void signOut() {
        this.isDeleted = true;
    }

}
