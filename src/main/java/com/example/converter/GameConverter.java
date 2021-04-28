package com.example.converter;

import com.example.dto.GameDTO;
import com.example.entity.Game;
import com.example.entity.Player;
import org.springframework.stereotype.Component;

@Component
public class GameConverter {
    public Game toEntity(GameDTO gameDTO) {
        Game gameEntity = new Game();
        gameEntity.setGameType(gameDTO.getGameType());
        gameEntity.setFirstPlayerPieceCode(gameDTO.getPiece());
        return gameEntity;
    }

    public GameDTO toDTO(Game gameEntity) {
        Player player = gameEntity.getFirstPlayerId();
        GameDTO gameDTO = new GameDTO();
        if (gameEntity.getSecondPlayerId() != null){
            Player secondPlayer = gameEntity.getSecondPlayerId();
            gameDTO.setSecondPlayerId(secondPlayer.getId());
        }
        gameDTO.setId(gameEntity.getId());
        gameDTO.setFirstPlayerId(player.getId());
        gameDTO.setGameType(gameEntity.getGameType());
        gameDTO.setPiece(gameEntity.getFirstPlayerPieceCode());
        return gameDTO;
    }

}
