package com.example.repository;

import com.example.entity.Game;
import com.example.entity.Move;
import com.example.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface MoveRepository extends JpaRepository<Move, Long> {
    List<Move> findByGameId(Game game);
    List<Move> findByGameIdAndPlayerId(Game game, Player player);
}
