package com.example.dto;

import com.example.enums.GameType;
import com.example.enums.Piece;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    @NotNull
    private Long id;

    @NotNull
    private Long firstPlayerId;

    private Long secondPlayerId;

    @NotNull
    private GameType gameType;

    @NotNull
    private Piece piece;
}


