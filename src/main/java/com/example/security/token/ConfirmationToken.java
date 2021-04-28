package com.example.security.token;

import com.example.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "player_id")
    private Player player;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, Player player) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.player = player;
    }
}
