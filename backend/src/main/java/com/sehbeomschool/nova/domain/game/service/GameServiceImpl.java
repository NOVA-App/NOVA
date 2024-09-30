package com.sehbeomschool.nova.domain.game.service;

import static com.sehbeomschool.nova.domain.game.constant.GameExceptionMessage.ALREADY_MARRIED;
import static com.sehbeomschool.nova.domain.game.constant.GameExceptionMessage.GAME_FINISHED;
import static com.sehbeomschool.nova.domain.game.constant.GameExceptionMessage.GAME_NOT_FINISHED;
import static com.sehbeomschool.nova.domain.game.constant.GameExceptionMessage.GAME_NOT_FOUND;
import static com.sehbeomschool.nova.domain.game.constant.GameExceptionMessage.IN_PROGRESS_GAME_ALREADY_EXIST;
import static com.sehbeomschool.nova.domain.game.constant.GameExceptionMessage.IN_PROGRESS_GAME_NOT_FOUND;
import static com.sehbeomschool.nova.domain.game.constant.GameExceptionMessage.USABLE_ASSET_NOT_ENOUGH;

import com.sehbeomschool.nova.domain.game.constant.EventType;
import com.sehbeomschool.nova.domain.game.constant.GameStatus;
import com.sehbeomschool.nova.domain.game.dao.AgesRepository;
import com.sehbeomschool.nova.domain.game.dao.AnalysisCommentRepository;
import com.sehbeomschool.nova.domain.game.dao.EventRepository;
import com.sehbeomschool.nova.domain.game.dao.GameRepository;
import com.sehbeomschool.nova.domain.game.domain.Ages;
import com.sehbeomschool.nova.domain.game.domain.AnnualAsset;
import com.sehbeomschool.nova.domain.game.domain.Event;
import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.game.domain.MyAssets;
import com.sehbeomschool.nova.domain.game.domain.OldAgeMonthlyAssets;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.GameStartRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.MarryRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.NextYearRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.UpdateLivingCostRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.CurrentYearResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.FixedCostResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.GameResultDetailResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.GameStartResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.InProgressGameResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.MyResultsListResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.RankingListResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.UpdateLivingCostResponseDto;
import com.sehbeomschool.nova.domain.game.exception.AlreadyMarryException;
import com.sehbeomschool.nova.domain.game.exception.GameFinishedException;
import com.sehbeomschool.nova.domain.game.exception.GameNotFinishedException;
import com.sehbeomschool.nova.domain.game.exception.GameNotFoundException;
import com.sehbeomschool.nova.domain.game.exception.InProgressGameAlreadyExistException;
import com.sehbeomschool.nova.domain.game.exception.UsableAssetNotEnoughException;
import com.sehbeomschool.nova.domain.news.service.NewsService;
import com.sehbeomschool.nova.domain.realty.domain.MyRealty;
import com.sehbeomschool.nova.domain.realty.service.RealtyManagerService;
import com.sehbeomschool.nova.domain.saving.service.SavingService;
import com.sehbeomschool.nova.domain.stock.service.StockManagerService;
import com.sehbeomschool.nova.domain.user.constant.UserExceptionMessage;
import com.sehbeomschool.nova.domain.user.dao.UserRepository;
import com.sehbeomschool.nova.domain.user.exception.UserNotFoundException;
import com.sehbeomschool.nova.global.constant.FixedValues;
import com.sehbeomschool.nova.global.util.VerifyUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameServiceImpl implements GameService {

    private final AgesRepository agesRepository;
    private final GameRepository gameRepository;
    private final AnalysisCommentRepository analysisCommentRepository;
    private final UserRepository userRepository;
    private final RealtyManagerService realtyManagerService;
    private final StockManagerService stockManagerService;
    private final NewsService newsService;
    private final SavingService savingService;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public GameStartResponseDto createGame(GameStartRequestDto gameStartRequestDto, Long userId) {
        int numOfInProgressGames = gameRepository.countInProgressGame(userId);

        if (numOfInProgressGames >= 1) {
            throw new InProgressGameAlreadyExistException(
                IN_PROGRESS_GAME_ALREADY_EXIST.getMessage());
        }

        AnnualAsset annualAsset = AnnualAsset.createStartAnnualAsset(
            gameStartRequestDto.getStartSalary());

        Game game = Game.builder()
            .user(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(
                UserExceptionMessage.NOT_EXIST_USER.getMessage())))
            .myAssets(
                MyAssets.createStartMyAsset(gameStartRequestDto.getStartSalary(), annualAsset))
            .annualAsset(annualAsset)
            .startSalary(gameStartRequestDto.getStartSalary())
            .gender(gameStartRequestDto.getGender())
            .currentAge(FixedValues.START_AGE.getValue().intValue())
            .build();

        Ages startAge = Ages.createStartAge(gameStartRequestDto.getStartSalary());

        game.addAgeAndSetThis(startAge);

        gameRepository.save(game);

        realtyManagerService.createRealtyInfoByGameStart(game);
        stockManagerService.createStocksInfoByGameStart(game.getAges().get(0));
        newsService.createNewsInfoByGameStart(game, game.getAges().get(0));

        return GameStartResponseDto.builder().gameId(game.getId()).build();
    }

    @Override
    @Transactional
    public GameStatus updateForNextYear(NextYearRequestDto nextYearRequestDto) {

        Game game = gameRepository.findById(nextYearRequestDto.getGameId())
            .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND.getMessage()));

        VerifyUser.verifyUser(game.getUser().getId());

        if (game.getCurrentAge() == FixedValues.END_AGE.getValue().intValue()) {
            throw new GameFinishedException(GAME_FINISHED.getMessage());
        }

        // 생활비, 고정 지출 지불된 현재 해 총 자산 Ages에 반영
        Ages currentAge = payAllCostsAndSetToCurrentAge(game);

        if ((currentAge.getAge() + 1) == FixedValues.END_AGE.getValue().intValue()) {
            OldAgeMonthlyAssets oldAgeMonthlyAssets = OldAgeMonthlyAssets.builder().game(game)
                .build();
            game.setOldAgeMonthlyAssets(oldAgeMonthlyAssets);
            game.setAnalysisComment(analysisCommentRepository.findBetweenMinAndMaxAsset(
                oldAgeMonthlyAssets.getTotalMonthlyAsset()));
            game.setResultAssets();
            game.increaseCurrentAge();
            game.setAssetGrowthRate();

            return GameStatus.FINISHED;
        }

        // 다음 해 Ages 생성 및 추가
        Ages nextAge = makeNextAge(game);
        game.getAges().add(nextAge);

        // 근로 소득 및 부동산 월세 수익 여유 자금에 추가
        long income = calculateAllIncome(game);
        game.getAnnualAsset().earnAsset(income);

        // 자녀 출산 이벤트 처리
        if (nextYearRequestDto.getIsChildBirth()) {
            addChildBirthEvent(game, nextAge);
        }

        // 주식 가격 변화 + newAge에 반영
        stockManagerService.createStocksInfoByNextYear(currentAge, nextAge);

        // 부동산 가격 변화
        realtyManagerService.updateRealtyInfoByNextYear(game.getId());
        realtyManagerService.updateMyRealtyByNextYear(game);

        // 뉴스 변화
        newsService.updateNewsInfoByNextYear(game, nextAge);

        savingService.updateInstallmentForNextYear(game.getId());
        savingService.updateIrpForNextYear(game.getId());

        // 주식 부동산 변경 된 가격 반영
        game.getMyAssets()
            .setRealtyAndStockAssetForNextYear(realtyManagerService.calRealtyAssetForNextYear(game),
                stockManagerService.calStockAssetForNextYear(game));

        // 다음 해 총 자산 저장 및 다음 해 Ages에 반영
        game.getMyAssets().recalculateTotalAsset();
        nextAge.setTotalAsset(game.getMyAssets().getTotalAsset());

        return GameStatus.IN_PROGRESS;
    }

    @Override
    public CurrentYearResponseDto readCurrentYear(Long gameId) {
        Game game = gameRepository.findById(gameId)
            .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND.getMessage()));

        VerifyUser.verifyUser(game.getUser().getId());

        return CurrentYearResponseDto.builder()
            .game(game)
            .events(game.getEvents())
            .myAssets(game.getMyAssets())
            .annualAsset(game.getAnnualAsset())
            .build();
    }


    @Override
    @Transactional
    public UpdateLivingCostResponseDto updateLivingCost(
        UpdateLivingCostRequestDto updateLivingCostRequestDto) {
        Game game = gameRepository.findById(updateLivingCostRequestDto.getGameId())
            .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND.getMessage()));

        VerifyUser.verifyUser(game.getUser().getId());

        game.getAnnualAsset().updateLivingCost(updateLivingCostRequestDto.getLivingCost());
        game.getMyAssets().recalculateTotalAsset();

        return UpdateLivingCostResponseDto.builder()
            .annualAsset(game.getAnnualAsset())
            .build();
    }

    @Override
    public FixedCostResponseDto readFixedCost(Long gameId) {
        Game game = gameRepository.findById(gameId)
            .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND.getMessage()));

        VerifyUser.verifyUser(game.getUser().getId());

        return FixedCostResponseDto.builder().annualAsset(game.getAnnualAsset()).build();
    }

    @Override
    @Transactional
    public void deleteGame(Long gameId) {
        Game game = gameRepository.findById(gameId)
            .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND.getMessage()));

        VerifyUser.verifyUser(game.getUser().getId());

        stockManagerService.deleteStocksInfo(game.getAges());
        realtyManagerService.deleteRealtyInfo(game.getId());
        newsService.deleteNewsInfo(game.getId());
        savingService.deleteInstallmentByGameId(game.getId());

        gameRepository.delete(game);
    }

    @Override
    public GameResultDetailResponseDto readGameResultDetail(Long gameId) {
        Game game = gameRepository.findById(gameId)
            .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND.getMessage()));

        VerifyUser.verifyUser(game.getUser().getId());

        if (game.getCurrentAge() != FixedValues.END_AGE.getValue().intValue()) {
            throw new GameNotFinishedException(GAME_NOT_FINISHED.getMessage());
        }

        return GameResultDetailResponseDto.builder().game(game).build();
    }

    @Override
    public MyResultsListResponseDto readAllMyGames(Long userId) {
        return MyResultsListResponseDto.builder()
            .games(gameRepository.findFinishedGameByUserId(userId))
            .build();
    }

    @Override
    public RankingListResponseDto readRankingList() {
        return RankingListResponseDto.builder()
            .games(gameRepository.findRankList(
                PageRequest.of(0, 30, Sort.by(Direction.DESC, "assetGrowthRate"))))
            .build();
    }

    @Override
    @Transactional
    public void marry(MarryRequestDto marryRequestDto) {
        Game game = gameRepository.findById(marryRequestDto.getGameId())
            .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND.getMessage()));

        VerifyUser.verifyUser(game.getUser().getId());

        if (eventRepository.findByGameIdAndEventType(marryRequestDto.getGameId(),
            EventType.MARRIAGE).isPresent()) {
            throw new AlreadyMarryException(ALREADY_MARRIED.getMessage());
        }

        game.addEventAndSetThis(Event.builder()
            .age(agesRepository.findCurrentAge(game))
            .eventType(EventType.MARRIAGE)
            .build());

        game.getMyAssets().recalculateTotalAsset();
    }

    @Override
    public InProgressGameResponseDto readInProgressGame(Long userId) {
        Game game = gameRepository.findInProgressGame(userId)
            .orElseThrow(() -> new GameNotFoundException(IN_PROGRESS_GAME_NOT_FOUND.getMessage()));

        return InProgressGameResponseDto.builder().game(game).build();
    }

    private void addChildBirthEvent(Game game, Ages nextAge) {
        VerifyUser.verifyUser(game.getUser().getId());

        game.addEventAndSetThis(Event.builder()
            .eventType(EventType.CHILD_BIRTH)
            .age(nextAge)
            .build());
    }

    private long calculateAllIncome(Game game) {
        long income = calculateNextSalary(game);
        log.debug("next salary : {}", income);

        for (MyRealty mr : game.getMyRealties()) {
            income += mr.getRentIncome();
        }
        return income;
    }

    private Ages makeNextAge(Game game) {
        game.increaseCurrentAge();
        Ages nextAge = Ages.builder()
            .age(game.getCurrentAge())
            .build();
        game.addAgeAndSetThis(nextAge);
        return nextAge;
    }

    private Ages payAllCostsAndSetToCurrentAge(Game game) {
        game.getAnnualAsset().payLivingAndFixedCost();
        if (game.getAnnualAsset().getTotalAnnualAsset() < 0) {
            throw new UsableAssetNotEnoughException(USABLE_ASSET_NOT_ENOUGH.getMessage());
        }

        game.getMyAssets().recalculateTotalAsset();
        Ages currentAge = game.getAges().get(game.getAges().size() - 1);
        currentAge.setTotalAsset(game.getMyAssets().getTotalAsset());
        return currentAge;
    }

    private long calculateNextSalary(Game game) {
        long salary = game.getStartSalary().longValue();

        for (int i = FixedValues.START_AGE.getValue().intValue(); i < game.getCurrentAge(); i++) {
            salary = (long) (salary * FixedValues.SALARY_INCREASE_RATE.getValue());
        }

        return salary;
    }
}
