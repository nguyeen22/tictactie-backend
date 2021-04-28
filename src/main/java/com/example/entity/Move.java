package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game gameId;

    @Column(nullable = false)
    private int boardRow;

    @Column(nullable = false)
    private int boardColumn;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = true)
    private Player playerId;

    @Column(nullable = false)
    private Date createdAt;

}
