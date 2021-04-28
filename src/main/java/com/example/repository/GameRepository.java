package com.example.repository;

import com.example.entity.Game;
import com.example.enums.GameStatus;
import com.example.enums.GameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByGameTypeAndGameStatus(GameType GameType, GameStatus GameStatus);
    Game findOneById (Long id);
}
