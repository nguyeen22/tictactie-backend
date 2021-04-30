package com.example.service.impl;

import com.example.converter.GameConverter;
import com.example.dto.GameDTO;
import com.example.entity.Game;
import com.example.enums.GameStatus;
import com.example.enums.GameType;
import com.example.repository.GameRepository;
import com.example.repository.PlayerRepository;
import com.example.service.IGameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class GameService implements IGameService {
    private final GameRepository gameRepository;
    private final GameConverter gameConverter;
    private final PlayerRepository playerRepository;

    @Override
    public GameDTO createNewGame(GameDTO gameDTO) {
        Game newGameEntity = gameConverter.toEntity(gameDTO);
        newGameEntity.setFirstPlayerId(playerRepository.findOneById(gameDTO.getFirstPlayerId()));
        newGameEntity.setGameStatus(gameDTO.getGameType() == GameType.COMPUTER ? GameStatus.IN_PROGRESS :
                    GameStatus.WAITS_FOR_PLAYER);
        newGameEntity.setFirstPlayerPieceCode(gameDTO.getPiece());
        newGameEntity.setCreatedAt(new Date());
        gameRepository.save(newGameEntity);
        return gameConverter.toDTO(newGameEntity);
    }
    public Game updateGameStatus(Game game, GameStatus gameStatus) {
        Game g = getGame(game.getId());
        g.setGameStatus(gameStatus);
        return g;
    }

    public Game getGame(Long id) {
        return gameRepository.findById(id).orElse(null);

    }

}
