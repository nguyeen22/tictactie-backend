package com.example.service.impl;

import com.example.converter.MoveConverter;
import com.example.dto.MoveDTO;
import com.example.entity.Game;
import com.example.entity.Move;
import com.example.entity.Player;
import com.example.entity.Position;
import com.example.enums.GameStatus;
import com.example.enums.GameType;
import com.example.repository.GameRepository;
import com.example.repository.MoveRepository;
import com.example.repository.PlayerRepository;
import com.example.service.GameLogic;
import com.example.service.IGameService;
import com.example.service.IMoveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MoveService implements IMoveService {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final MoveConverter moveConverter;
    private final MoveRepository moveRepository;
    private final IGameService gameService;

    @Override
    public MoveDTO createNewMove(Game game, MoveDTO moveDTO) {
        Move newMove = moveConverter.toEntity(moveDTO);
        newMove.setGameId(game);
        newMove.setPlayerId(playerRepository.findOneById(moveDTO.getPlayerId()));
        newMove.setCreatedAt(new Date());
        newMove.setBoardRow(moveDTO.getBoardRow());
        newMove.setBoardColumn(moveDTO.getBoardColumn());
        moveRepository.save(newMove);

        return moveConverter.toDTO(newMove);
    }

    public Move autoCreateMove(Game game) {
        Move move = new Move();
        move.setBoardColumn(GameLogic.nextAutoMove(getTakenMovePositionsInGame(game)).getBoardColumn());
        move.setBoardRow(GameLogic.nextAutoMove(getTakenMovePositionsInGame(game)).getBoardRow());
        move.setCreatedAt(new Date());
        move.setPlayerId(null);
        move.setGameId(game);
        moveRepository.save(move);

        return move;
    }

    public List<Position> getTakenMovePositionsInGame(Game game) {
        return moveRepository.findByGameId(game).stream()
                .map(move -> new Position(move.getBoardRow(), move.getBoardColumn()))
                .collect(Collectors.toList());
    }

    public GameStatus checkCurrentGameStatus(Game game) {
        if (GameLogic.isWinner(getPlayerMovePositionsInGame(game, game.getFirstPlayerId()))) {
            return GameStatus.FIRST_PLAYER_WON;
        } else if (GameLogic.isWinner(getPlayerMovePositionsInGame(game, game.getSecondPlayerId()))) {
            return GameStatus.SECOND_PLAYER_WON;
        } else if (GameLogic.isBoardIsFull(getTakenMovePositionsInGame(game))) {
            return GameStatus.TIE;
        } else if (game.getGameType() == GameType.COMPETITION && game.getSecondPlayerId() == null) {
            return GameStatus.WAITS_FOR_PLAYER;
        } else {
            return GameStatus.IN_PROGRESS;
        }

    }

    public List<Position> getPlayerMovePositionsInGame(Game game, Player player) {

        return moveRepository.findByGameIdAndPlayerId(game, player).stream()
                .map(move -> new Position(move.getBoardRow(), move.getBoardColumn()))
                .collect(Collectors.toList());
    }
}