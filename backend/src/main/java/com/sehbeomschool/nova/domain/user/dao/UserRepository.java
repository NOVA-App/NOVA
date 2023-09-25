package com.sehbeomschool.nova.domain.user.dao;

import com.sehbeomschool.nova.domain.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findBySocialId(Long socialId);
}
