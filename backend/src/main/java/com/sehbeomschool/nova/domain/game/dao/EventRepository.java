package com.sehbeomschool.nova.domain.game.dao;

import com.sehbeomschool.nova.domain.game.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
