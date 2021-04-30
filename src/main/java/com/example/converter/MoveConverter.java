package com.example.converter;

import com.example.dto.MoveDTO;
import com.example.entity.Game;
import com.example.entity.Move;
import com.example.entity.Player;
import com.example.service.IGameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@AllArgsConstructor
public class MoveConverter {
    private final IGameService gameService;
    public Move toEntity(MoveDTO moveDTO){
        Move moveEntity = new Move();

        moveEntity.setGameId(gameService.getGame(moveDTO.getGameId()));
        moveEntity.setBoardColumn(moveDTO.getBoardColumn());
        moveEntity.setBoardRow(moveDTO.getBoardRow());
        moveEntity.setCreatedAt(new Date());
        return moveEntity;
    }
    public MoveDTO toDTO(Move moveEntity){
        Player player = moveEntity.getPlayerId();
        Game game = moveEntity.getGameId();
        MoveDTO moveDTO = new MoveDTO();
        moveDTO.setBoardColumn(moveEntity.getBoardColumn());
        moveDTO.setBoardRow(moveEntity.getBoardRow());
        moveDTO.setGameId(game.getId());
        moveDTO.setPlayerId(player.getId());
        return moveDTO;

    }
}
