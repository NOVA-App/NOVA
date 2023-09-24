package com.sehbeomschool.nova.domain.saving.dao;

import com.sehbeomschool.nova.domain.saving.domain.InsInterest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsInterestRepository extends JpaRepository<InsInterest, Long> {

    Optional<InsInterest> findByPeriod(int period);
}
