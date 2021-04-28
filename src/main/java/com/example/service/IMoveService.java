package com.example.service;

import com.example.dto.CreateMoveDTO;
import com.example.dto.MoveDTO;
import com.example.entity.Game;
import com.example.entity.Move;
import com.example.entity.Player;
import com.example.entity.Position;
import com.example.enums.GameStatus;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface IMoveService {
    Move createMove(Game game, Player player, CreateMoveDTO createMoveDTO);
    MoveDTO createNewMove(MoveDTO moveDTO);
    Move autoCreateMove(Game game);
    List<Position> getTakenMovePositionsInGame(Game game);
    GameStatus checkCurrentGameStatus(Game game);

}
