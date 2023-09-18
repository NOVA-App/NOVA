package com.sehbeomschool.nova.domain.game.domain;

import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnalysisComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANALYSIS_COMMENT_ID")
    private Long id;

    private String eatOutComment;

    private String tripComment;

    private String carComment;

    private String hobbyComment;

    private String generalComment;

    private Integer minAsset;

    private Integer maxAsset;

    @Builder
    public AnalysisComment(Long id, String eatOutComment, String tripComment, String carComment,
        String hobbyComment, String generalComment, Integer minAsset, Integer maxAsset) {
        this.id = id;
        this.eatOutComment = eatOutComment;
        this.tripComment = tripComment;
        this.carComment = carComment;
        this.hobbyComment = hobbyComment;
        this.generalComment = generalComment;
        this.minAsset = minAsset;
        this.maxAsset = maxAsset;
    }
}
