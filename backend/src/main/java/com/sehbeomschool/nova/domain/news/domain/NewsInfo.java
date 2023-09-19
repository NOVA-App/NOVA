package com.sehbeomschool.nova.domain.news.domain;

import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewsInfo extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "NEWS_INFO_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "NEWS_ID")
    private News news;

    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private Game game;
}
