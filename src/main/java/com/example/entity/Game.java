package com.example.entity;


import com.example.enums.GameStatus;
import com.example.enums.GameType;
import com.example.enums.Piece;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.Date;

@Entity
@Check(constraints = "first_player_piece_code = 'O' or first_player_piece_code = 'X' " +
        "and game_type = 'COMPUTER' or game_type = 'COMPETITION' or game_type = 'OFFLINE' " +
        "and game_status = 'IN_PROGRESS' or game_status = 'FIRST_PLAYER_WON' or game_status = 'SECOND_PLAYER_WON'" +
        "or game_status = 'TIE' or game_status = 'WAITS_FOR_PLAYER' ")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "first_player_id", nullable = false)
    private Player firstPlayerId;

    @ManyToOne
    @JoinColumn(name = "second_player_id", nullable = true)
    private Player secondPlayerId;

    @Enumerated(EnumType.STRING)
    private Piece firstPlayerPieceCode;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @Column(nullable = false)
    private Date createdAt;
}
