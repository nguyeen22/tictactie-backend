package com.example.service;

import com.example.dto.MoveDTO;
import com.example.entity.Game;
import com.example.entity.Move;
import com.example.entity.Position;
import com.example.enums.GameStatus;

import java.util.List;

public interface IMoveService {
    MoveDTO createNewMove(Game game, MoveDTO moveDTO);
    Move autoCreateMove(Game game);
    List<Position> getTakenMovePositionsInGame(Game game);
    GameStatus checkCurrentGameStatus(Game game);

}
