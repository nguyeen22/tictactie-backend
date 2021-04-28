package com.example.service;

import com.example.dto.GameDTO;
import com.example.entity.Game;
import com.example.enums.GameStatus;

public interface IGameService {
    GameDTO createNewGame(GameDTO gameDTO);
    Game getGame(Long id);
    Game updateGameStatus(Game game, GameStatus gameStatus);

}

