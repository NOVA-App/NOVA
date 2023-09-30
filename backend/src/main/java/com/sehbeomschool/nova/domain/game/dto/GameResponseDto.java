package com.sehbeomschool.nova.domain.game.dto;

import com.sehbeomschool.nova.domain.game.constant.EventType;
import com.sehbeomschool.nova.domain.game.constant.Gender;
import com.sehbeomschool.nova.domain.game.domain.Ages;
import com.sehbeomschool.nova.domain.game.domain.AnalysisComment;
import com.sehbeomschool.nova.domain.game.domain.AnnualAsset;
import com.sehbeomschool.nova.domain.game.domain.Event;
import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.game.domain.MyAssets;
import com.sehbeomschool.nova.domain.game.domain.OldAgeMonthlyAssets;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.UserInfoResponseDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class GameResponseDto {

    @Getter
    public static class GameStartResponseDto {

        private Long gameId;

        @Builder
        public GameStartResponseDto(Long gameId) {
            this.gameId = gameId;
        }
    }

    @Getter
    public static class FixedCostResponseDto {

        private Long totalFixedCost;
        private Long monthlyRentCost;
        private Long IRPCost;
        private Long childCost;
        private Long loansCost;
        private Long installmentSavingCost;

        @Builder
        public FixedCostResponseDto(AnnualAsset annualAsset) {
            this.totalFixedCost = annualAsset.sumOfFixedCost();
            this.monthlyRentCost = annualAsset.getMonthlyRentCost();
            this.IRPCost = annualAsset.getIRPCost();
            this.childCost = annualAsset.getChildCost();
            this.loansCost = annualAsset.getLoansCost();
            this.installmentSavingCost = annualAsset.getInstallmentSavingCost();
        }
    }

    @Getter
    public static class UpdateLivingCostResponseDto {

        private Long usableAsset;
        private Long livingCost;
        private FixedCostResponseDto fixedCost;

        @Builder
        public UpdateLivingCostResponseDto(AnnualAsset annualAsset) {
            this.usableAsset = annualAsset.getUsableAsset();
            this.livingCost = annualAsset.getLivingCost();
            this.fixedCost = FixedCostResponseDto.builder().annualAsset(annualAsset).build();
        }
    }

    @Getter
    public static class CurrentYearResponseDto {

        private Long gameId;
        private Gender gender;
        private Integer currentAge;
        private Boolean isMarried;
        private Integer numOfChild;
        private UpdateLivingCostResponseDto annualAssets;
        private MyAssetsResponseDto myAssets;

        @Builder
        public CurrentYearResponseDto(Game game, List<Event> events, MyAssets myAssets,
            AnnualAsset annualAsset) {
            this.gameId = game.getId();
            this.gender = game.getGender();
            this.currentAge = game.getCurrentAge();
            setEventInfo(events);
            this.annualAssets = UpdateLivingCostResponseDto.builder()
                .annualAsset(annualAsset)
                .build();
            this.myAssets = MyAssetsResponseDto.builder()
                .myAssets(myAssets)
                .build();
        }

        private void setEventInfo(List<Event> events) {
            this.isMarried = false;
            this.numOfChild = 0;

            for (Event e : events) {
                if (e.getEventType() == EventType.MARRIAGE) {
                    this.isMarried = true;
                    continue;
                }

                this.numOfChild += 1;
            }
        }
    }

    @Getter
    public static class MyAssetsResponseDto {

        private Long totalAsset;
        private Long stocksAsset;
        private Long realtyAsset;
        private Long savingAsset;
        private Long loanAsset;

        @Builder
        public MyAssetsResponseDto(MyAssets myAssets) {
            this.totalAsset = myAssets.getTotalAsset();
            this.stocksAsset = myAssets.getStockAsset();
            this.realtyAsset = myAssets.getRealtyAsset();
            this.savingAsset = myAssets.getIRPAsset() + myAssets.getInstallmentSavingAsset();
            this.loanAsset = myAssets.getLoanAsset();
        }
    }

    @Getter
    public static class GameResultDetailResponseDto {

        private Long totalAssets;
        private Integer oldAgeMonthlyAssets;
        private UserInfoResponseDto userInfo;
        private GraphInfoResponseDto graphInfo;
        private AssetsInfoResponseDto assetsInfo;
        private OldAgeAssetsInfoResponseDto oldAgeAssetsInfo;
        private AnalysisCommentResponseDto analysisComment;

        @Builder
        public GameResultDetailResponseDto(Game game) {
            this.totalAssets = game.getResultAssets();
            this.oldAgeMonthlyAssets = game.getOldAgeMonthlyAssets().getTotalMonthlyAsset();
            this.userInfo = UserInfoResponseDto.builder().user(game.getUser()).build();
            this.graphInfo = GraphInfoResponseDto.builder()
                .events(game.getEvents())
                .ages(game.getAges())
                .build();
            this.assetsInfo = AssetsInfoResponseDto.builder()
                .annualAsset(game.getAnnualAsset())
                .myAssets(game.getMyAssets())
                .build();
            this.oldAgeAssetsInfo = OldAgeAssetsInfoResponseDto.builder()
                .oldAgeMonthlyAssets(game.getOldAgeMonthlyAssets())
                .build();
            this.analysisComment = AnalysisCommentResponseDto.builder()
                .analysisComment(game.getAnalysisComment())
                .build();
        }
    }

    @Getter
    public static class GraphInfoResponseDto {

        private Integer marriedAge;
        private List<Integer> childBirthAges = new ArrayList<>();
        private List<Long> totalAssetsPerAge = new ArrayList<>();

        @Builder
        public GraphInfoResponseDto(List<Event> events, List<Ages> ages) {
            for (Event e : events) {
                if (e.getEventType() == EventType.MARRIAGE) {
                    this.marriedAge = e.getAge().getAge();
                }

                if (e.getEventType() == EventType.CHILD_BIRTH) {
                    this.childBirthAges.add(e.getAge().getAge());
                }
            }

            for (Ages a : ages) {
                this.totalAssetsPerAge.add(a.getTotalAsset());
            }
        }
    }

    @Getter
    public static class AssetsInfoResponseDto {

        private Long usableAsset;
        private Long IRPAsset;
        private Long installmentSavingAsset;
        private Long stockAsset;
        private Long realtyAsset;
        private Long loanAsset;
        private Long totalTax;

        @Builder
        public AssetsInfoResponseDto(AnnualAsset annualAsset, MyAssets myAssets) {
            this.usableAsset = annualAsset.getUsableAsset();
            this.IRPAsset = myAssets.getIRPAsset();
            this.installmentSavingAsset = myAssets.getInstallmentSavingAsset();
            this.stockAsset = myAssets.getStockAsset();
            this.realtyAsset = myAssets.getRealtyAsset();
            this.loanAsset = myAssets.getLoanAsset();
            this.totalTax = myAssets.getTotalTax();
        }
    }

    @Getter
    public static class OldAgeAssetsInfoResponseDto {

        private Integer monthlyAmount;
        private Integer childAllowance;
        private Integer realtyRentIncome;
        private Integer nationalPension;
        private Integer totalMonthlyAsset;

        @Builder
        public OldAgeAssetsInfoResponseDto(OldAgeMonthlyAssets oldAgeMonthlyAssets) {
            this.monthlyAmount = oldAgeMonthlyAssets.getMonthlyAmount();
            this.childAllowance = oldAgeMonthlyAssets.getChildAllowance();
            this.realtyRentIncome = oldAgeMonthlyAssets.getRealtyRentIncome();
            this.nationalPension = oldAgeMonthlyAssets.getNationalPension();
            this.totalMonthlyAsset = oldAgeMonthlyAssets.getTotalMonthlyAsset();
        }
    }

    @Getter
    public static class AnalysisCommentResponseDto {

        private String eatOutComment;
        private String tripComment;
        private String carComment;
        private String hobbyComment;
        private String generalComment;

        @Builder
        public AnalysisCommentResponseDto(AnalysisComment analysisComment) {
            this.eatOutComment = analysisComment.getEatOutComment();
            this.tripComment = analysisComment.getTripComment();
            this.carComment = analysisComment.getCarComment();
            this.hobbyComment = analysisComment.getHobbyComment();
            this.generalComment = analysisComment.getGeneralComment();
        }
    }

    @Getter
    public static class MyResultsListResponseDto {

        private List<MyResultsInfoResponseDto> myResults = new ArrayList<>();

        @Builder
        public MyResultsListResponseDto(List<Game> games) {
            games.forEach(g -> this.myResults.add(
                MyResultsInfoResponseDto.builder()
                    .game(g)
                    .build()
            ));
        }
    }

    @Getter
    public static class MyResultsInfoResponseDto {

        private Long gameId;
        private Integer startSalary;
        private Long resultAssets;
        private Double assetGrowthRate;
        private LocalDateTime createdAt;

        @Builder
        public MyResultsInfoResponseDto(Game game) {
            this.gameId = game.getId();
            this.startSalary = game.getStartSalary();
            this.resultAssets = game.getResultAssets();
            this.assetGrowthRate = game.getAssetGrowthRate();
            this.createdAt = game.getCreatedAt();
        }
    }

    @Getter
    public static class RankingListResponseDto {

        List<RankingInfoResponseDto> rankResults = new ArrayList<>();

        @Builder
        public RankingListResponseDto(List<Game> games) {
            games.forEach(
                g -> this.rankResults.add(RankingInfoResponseDto.builder().game(g).build()));
        }
    }

    @Getter
    public static class RankingInfoResponseDto {

        private UserInfoResponseDto userInfo;
        private RankingResultInfoResponseDto resultInfo;

        @Builder
        public RankingInfoResponseDto(Game game) {
            this.userInfo = UserInfoResponseDto.builder().user(game.getUser()).build();
            this.resultInfo = RankingResultInfoResponseDto.builder().game(game).build();
        }
    }

    @Getter
    public static class RankingResultInfoResponseDto {

        private Long gameId;
        private Double assetGrowthRate;

        @Builder
        public RankingResultInfoResponseDto(Game game) {
            this.gameId = game.getId();
            this.assetGrowthRate = game.getAssetGrowthRate();
        }
    }
}
