package com.sehbeomschool.nova.domain.news.domain;

import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NEWS_ID")
    private News news;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @Builder
    public NewsInfo(Long id, News news, Game game) {
        this.id = id;
        this.news = news;
        this.game = game;
    }

    public void setNews(News news){
        this.news = news;
    }
}
