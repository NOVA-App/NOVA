package com.sehbeomschool.nova.domain.saving.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.sehbeomschool.nova.domain.game.dao.GameRepository;
import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.game.domain.MyAssets;
import com.sehbeomschool.nova.domain.game.exception.GameNotFoundException;
import com.sehbeomschool.nova.domain.saving.dao.InsInterestRepository;
import com.sehbeomschool.nova.domain.saving.dao.SavingRepository;
import com.sehbeomschool.nova.domain.saving.domain.InstallmentSavings;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("금융 서비스 단위 테스트")
class SavingServiceTest {

    @InjectMocks
    private SavingServiceImpl savingService;

    @Mock
    private SavingRepository savingRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private InsInterestRepository insInterestRepository;

    @Nested
    @DisplayName("나의 금융 현황 불러오기 with gameId")
    class readSavingInfo {

        @Nested
        @DisplayName("게임이 존재하지 않으면")
        class not_exist_game {

            @Test
            @DisplayName("게임 존재하지 않음 예외 발생")
            void exception() {
                given(gameRepository.findById(any())).willReturn(Optional.empty());

                assertThatThrownBy(() -> savingService.readSavingInfo(any())).isInstanceOf(
                    GameNotFoundException.class
                );
            }
        }

        @Nested
        @DisplayName("게임이 존재하면")
        class exist_game {

            @Test
            @DisplayName("금융현황을 반환한다.")
            void readSavingInfo() {

                InstallmentSavings installmentSavings = InstallmentSavings.builder().build();
                Game game = Game.builder().myAssets(MyAssets.builder().build()).build();
                given(gameRepository.findById(any())).willReturn(Optional.of(game));
                given(savingRepository.findByGameId(any())).willReturn(
                    Optional.of(List.of(installmentSavings)));

                savingService.readSavingInfo(any());

                then(gameRepository).should().findById(any());
                then(savingRepository).should().findByGameId(any());

            }
        }

    }


    @Test
    void createInstallment() {
    }

    @Test
    void deleteInstallment() {
    }

    @Test
    void matureInstallment() {
    }

    @Test
    void updateInstallmentForNextYear() {
    }

    @Test
    void updateIrpCost() {
    }

    @Test
    void updateIrpForNextYear() {
    }

    @Test
    void deleteInstallmentByGameId() {
    }
}