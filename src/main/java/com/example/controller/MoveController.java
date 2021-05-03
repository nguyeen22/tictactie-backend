package com.example.controller;

import com.example.converter.MoveConverter;
import com.example.dto.MoveDTO;
import com.example.entity.Game;
import com.example.entity.Move;
import com.example.service.IGameService;
import com.example.service.IMoveService;
import com.example.service.IPlayerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/move")
@AllArgsConstructor
public class MoveController {

    private final IPlayerService playerService;
    private final IMoveService moveService;
    private final IGameService gameService;
    private final HttpSession httpSession;
    private final MoveConverter moveConverter;

    private static final Logger logger = LoggerFactory.getLogger(MoveController.class);

    @PostMapping("/create")
    public MoveDTO create(@RequestBody MoveDTO moveDTO) {
        httpSession.setAttribute("gameId", moveDTO.getGameId());
        Long gameId = (Long) httpSession.getAttribute("gameId");
        logger.info("move to insert: Column: " + moveDTO.getBoardColumn() + " Row: " + moveDTO.getBoardRow());
            MoveDTO move = moveService.createNewMove(gameService.getGame(gameId), moveDTO);
        Game game = gameService.getGame(gameId);
        gameService.updateGameStatus(gameService.getGame(gameId), moveService.checkCurrentGameStatus(game));
        return move;

    }

    @GetMapping("/auto")
    public Move autoCreate() {
        Long gameId = (Long) httpSession.getAttribute("gameId");
        logger.info("Computer insert move");
        Move move = moveService.autoCreateMove(gameService.getGame(gameId));
        Game game = gameService.getGame(gameId);
        gameService.updateGameStatus(gameService.getGame(gameId), moveService.checkCurrentGameStatus(game));
        return move;

    }


}
