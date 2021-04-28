package com.example.converter;

import com.example.dto.MoveDTO;
import com.example.entity.Game;
import com.example.entity.Move;
import com.example.entity.Player;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class MoveConverter {

    public Move toEntity(MoveDTO moveDTO){
        Move moveEntity = new Move();
        Game g = new Game();
        g.setId(moveDTO.getGameID());
        moveEntity.setGameId(g);

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
        moveDTO.setGameID(game.getId());
        moveDTO.setPlayerID(player.getId());
        return moveDTO;

    }
}
