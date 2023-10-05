package com.sehbeomschool.nova.domain.game.dao;

import com.sehbeomschool.nova.domain.game.constant.EventType;
import com.sehbeomschool.nova.domain.game.domain.Event;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByGameIdAndEventType(Long gameId, EventType eventType);
}
