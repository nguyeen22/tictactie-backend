package com.example.repository;

import com.example.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByUserName(String userName);
    Player findOneById(Long id);
    Player findOneByUserName(String userName);
    Player findByUserNameAndPassword(String userName, String password);

    @Transactional
    @Modifying
    @Query("UPDATE Player a " +
            "SET a.enabled = TRUE WHERE a.userName = ?1")
    int enablePlayerAccount(String username);
}
