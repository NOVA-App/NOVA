package com.sehbeomschool.nova.domain.game.service;

import com.sehbeomschool.nova.domain.game.dao.AgesRepository;
import com.sehbeomschool.nova.domain.game.dao.AnalysisCommentRepository;
import com.sehbeomschool.nova.domain.game.dao.EventRepository;
import com.sehbeomschool.nova.domain.game.dao.GameRepository;
import com.sehbeomschool.nova.domain.game.dao.MonthlyCostRepository;
import com.sehbeomschool.nova.domain.game.dao.MyAssetsRepository;
import com.sehbeomschool.nova.domain.game.dao.OldAgeMonthlyAssetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final AgesRepository agesRepository;
    private final AnalysisCommentRepository analysisCommentRepository;
    private final EventRepository eventRepository;
    private final GameRepository gameRepository;
    private final MonthlyCostRepository monthlyCostRepository;
    private final MyAssetsRepository myAssetsRepository;
    private final OldAgeMonthlyAssetsRepository oldAgeMonthlyAssetsRepository;

}
