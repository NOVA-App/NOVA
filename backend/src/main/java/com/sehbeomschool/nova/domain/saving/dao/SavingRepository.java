package com.sehbeomschool.nova.domain.saving.dao;

import com.sehbeomschool.nova.domain.saving.domain.InstallmentSavings;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingRepository extends JpaRepository<InstallmentSavings, Long> {

    Optional<List<InstallmentSavings>> findByGameId(Long gameId);

    void deleteByGameId(Long gameId);
}
