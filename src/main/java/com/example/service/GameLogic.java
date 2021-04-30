package com.example.service;

import com.example.entity.Game;
import com.example.entity.Position;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class GameLogic {

    private Game game;

    public static boolean isWinner(List<Position> positions) {

        return getWinningPositions().stream().anyMatch(positions::containsAll);
    }

    public static List<List<Position>> getWinningPositions() {
        List<List<Position>> winingPositions = new ArrayList<>();
        int n = 0;
        int m = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 6; j++) {
                winingPositions.add(asList(new Position(n + i, m + j),
                        new Position(n + i, m + j + 1),
                        new Position(n + i, m + j + 2),
                        new Position(n + i, m + j + 3),
                        new Position(n + i, m + j + 4)));
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 6; j++) {
                winingPositions.add(asList(new Position(n + j, m + i),
                        new Position(n + j + 1, m + i),
                        new Position(n + j + 2, m + i),
                        new Position(n + j + 3, m + i),
                        new Position(n + j + 4, m + i)));
            }
        }
        for (int i = 0; i < 6; i++) {
            winingPositions.add(asList(new Position(n + i, m + i),
                    new Position(n + i + 1, m + i + 1),
                    new Position(n + i + 2, m + i + 2),
                    new Position(n + i + 3, m + i + 3),
                    new Position(n + i + 4, m + i + 4)));
        }
        for (int i = 0; i < 6; i++) {
            winingPositions.add(asList(new Position(n + i, m - i + 9),
                    new Position(n + i + 1, m - i + 8),
                    new Position(n + i + 2, m - i + 7),
                    new Position(n + i + 3, m - i + 6),
                    new Position(n + i + 4, m - i + 5)));
        }
        for (int i = 0; i <= 4; i++) {
            winingPositions.add(asList(new Position(n + i + 1, m + i),
                    new Position(n + i + 2, m + i + 2),
                    new Position(n + i + 3, m + i + 2),
                    new Position(n + i + 4, m + i + 3),
                    new Position(n + i + 5, m + i + 4)));
        }
        for (int i = 0; i <= 3; i++) {
            winingPositions.add(asList(new Position(n + i + 2, m + i),
                    new Position(n + i + 3, m + i + 1),
                    new Position(n + i + 4, m + i + 2),
                    new Position(n + i + 5, m + i + 3),
                    new Position(n + i + 6, m + i + 4)));
        }
        for (int i = 0; i <= 2; i++) {
            winingPositions.add(asList(new Position(n + i + 3, m + i),
                    new Position(n + i + 4, m + i + 1),
                    new Position(n + i + 5, m + i + 2),
                    new Position(n + i + 6, m + i + 3),
                    new Position(n + i + 7, m + i + 4)));
        }
        for (int i = 0; i <= 1; i++) {
            winingPositions.add(asList(new Position(n + i + 4, m + i),
                    new Position(n + i + 5, m + i + 1),
                    new Position(n + i + 6, m + i + 2),
                    new Position(n + i + 7, m + i + 3),
                    new Position(n + i + 8, m + i + 4)));
        }
        for (int i = 0; i <= 0; i++) {
            winingPositions.add(asList(new Position(n + i + 5, m + i),
                    new Position(n + i + 6, m + i + 1),
                    new Position(n + i + 7, m + i + 2),
                    new Position(n + i + 8, m + i + 3),
                    new Position(n + i + 9, m + i + 4)));
        }
        for (int i = 0; i <= 4; i++) {
            winingPositions.add(asList(new Position(n + i, m + i + 1),
                    new Position(n + i + 1, m + i + 2),
                    new Position(n + i + 2, m + i + 3),
                    new Position(n + i + 3, m + i + 4),
                    new Position(n + i + 4, m + i + 5)));
        }
        for (int i = 0; i <= 3; i++) {
            winingPositions.add(asList(new Position(n + i, m + i + 2),
                    new Position(n + i + 1, m + i + 3),
                    new Position(n + i + 2, m + i + 4),
                    new Position(n + i + 3, m + i + 5),
                    new Position(n + i + 4, m + i + 6)));
        }
        for (int i = 0; i <= 2; i++) {
            winingPositions.add(asList(new Position(n + i, m + i + 3),
                    new Position(n + i + 2, m + i + 4),
                    new Position(n + i + 3, m + i + 5),
                    new Position(n + i + 4, m + i + 6),
                    new Position(n + i + 5, m + i + 7)));
        }
        for (int i = 0; i <= 1; i++) {
            winingPositions.add(asList(new Position(n + i, m + i + 4),
                    new Position(n + i + 1, m + i + 5),
                    new Position(n + i + 2, m + i + 6),
                    new Position(n + i + 3, m + i + 7),
                    new Position(n + i + 4, m + i + 8)));
        }
        for (int i = 0; i <= 0; i++) {
            winingPositions.add(asList(new Position(n + i, m + i + 5),
                    new Position(n + i + 1, m + i + 6),
                    new Position(n + i + 2, m + i + 7),
                    new Position(n + i + 3, m + i + 8),
                    new Position(n + i + 4, m + i + 9)));
        }
        for (int i = 0; i <= 4; i++) {
            winingPositions.add(asList(new Position(n + i, m - i + 8),
                    new Position(n + i + 1, m - i + 7),
                    new Position(n + i + 2, m - i + 6),
                    new Position(n + i + 3, m - i + 5),
                    new Position(n + i + 4, m - i + 4)));
        }
        for (int i = 0; i <= 3; i++) {
            winingPositions.add(asList(new Position(n + i, m - i + 7),
                    new Position(n + i + 1, m - i + 6),
                    new Position(n + i + 2, m - i + 5),
                    new Position(n + i + 3, m - i + 4),
                    new Position(n + i + 4, m - i + 3)));
        }
        for (int i = 0; i <= 2; i++) {
            winingPositions.add(asList(new Position(n + i, m - i + 6),
                    new Position(n + i + 1, m - i + 5),
                    new Position(n + i + 2, m - i + 4),
                    new Position(n + i + 3, m - i + 3),
                    new Position(n + i + 4, m - i + 2)));
        }
        for (int i = 0; i <= 1; i++) {
            winingPositions.add(asList(new Position(n + i, m - i + 5),
                    new Position(n + i + 1, m - i + 4),
                    new Position(n + i + 2, m - i + 3),
                    new Position(n + i + 3, m - i + 2),
                    new Position(n + i + 4, m - i + 1)));
        }
        for (int i = 0; i <= 0; i++) {
            winingPositions.add(asList(new Position(n + i, m - i + 4),
                    new Position(n + i + 1, m - i + 3),
                    new Position(n + i + 2, m - i + 2),
                    new Position(n + i + 3, m - i + 1),
                    new Position(n + i + 4, m - i + 0)));
        }
        for (int i = 0; i <= 4; i++) {
            winingPositions.add(asList(new Position(n + i + 1, m - i + 9),
                    new Position(n + i + 2, m - i + 8),
                    new Position(n + i + 3, m - i + 7),
                    new Position(n + i + 4, m - i + 6),
                    new Position(n + i + 5, m - i + 5)));
        }
        for (int i = 0; i <= 3; i++) {
            winingPositions.add(asList(new Position(n + i + 2, m - i + 9),
                    new Position(n + i + 3, m - i + 8),
                    new Position(n + i + 4, m - i + 7),
                    new Position(n + i + 5, m - i + 6),
                    new Position(n + i + 6, m - i + 5)));
        }
        for (int i = 0; i <= 2; i++) {
            winingPositions.add(asList(new Position(n + i + 3, m - i + 9),
                    new Position(n + i + 4, m - i + 8),
                    new Position(n + i + 5, m - i + 7),
                    new Position(n + i + 6, m - i + 6),
                    new Position(n + i + 7, m - i + 5)));
        }
        for (int i = 0; i <= 1; i++) {
            winingPositions.add(asList(new Position(n + i + 4, m - i + 9),
                    new Position(n + i + 5, m - i + 8),
                    new Position(n + i + 6, m - i + 7),
                    new Position(n + i + 7, m - i + 6),
                    new Position(n + i + 8, m - i + 5)));
        }
        for (int i = 0; i <= 0; i++) {
            winingPositions.add(asList(new Position(n + i + 5, m - i + 9),
                    new Position(n + i + 6, m - i + 8),
                    new Position(n + i + 7, m - i + 7),
                    new Position(n + i + 8, m - i + 6),
                    new Position(n + i + 9, m - i + 5)));
        }
        return winingPositions;
    }


    /**
     * Stores all pairs of available rows and columns
     *
     * @return list of all board's positions
     */
    public static List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                positions.add(new Position(row, col));
            }
        }
        return positions;
    }

    /**
     * @param numberOfFirstPlayerMovesInGame
     * @param numberOfSecondPlayerMovesInGame
     * @return true or false depending on the count of the player's moves
     */
    public static boolean playerTurn(int numberOfFirstPlayerMovesInGame, int numberOfSecondPlayerMovesInGame) {
        return numberOfFirstPlayerMovesInGame == numberOfSecondPlayerMovesInGame || numberOfFirstPlayerMovesInGame == 0;
    }

    public static boolean isBoardIsFull(List<Position> takenPositions) {
        return takenPositions.size() == 100;
    }

    // GENERATE COMPUTER'S MOVES
    public static List<Position> getOpenPositions(List<Position> takenPositions) {
        return getAllPositions().stream().filter(p -> !takenPositions.contains(p)).collect(Collectors.toList());
    }

    public static Position nextAutoMove(List<Position> takenPositions) {
        return getOpenPositions(takenPositions).get(0);

    }


}
