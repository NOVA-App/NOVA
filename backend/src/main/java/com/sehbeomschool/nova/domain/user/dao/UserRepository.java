package com.sehbeomschool.nova.domain.user.dao;

import com.sehbeomschool.nova.domain.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.id = :userId and u.isDeleted = false")
    Optional<User> findById(@Param("userId") Long userId);

    Optional<User> findBySocialId(Long socialId);
}
