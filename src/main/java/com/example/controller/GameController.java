package com.example.controller;

import com.example.dto.GameDTO;
import com.example.service.IGameService;
import com.example.service.IPlayerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final IGameService gameService;
    private final IPlayerService playerService;
    private final HttpSession httpSession;
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @PostMapping("/new")
    public GameDTO createNew(@RequestBody GameDTO gameDTO) {
        GameDTO game = gameService.createNewGame(gameDTO);
        httpSession.setAttribute("gameId", game.getId());

        logger.info("New game id: " + httpSession.getAttribute("gameId")+ " stored in session" );
        return game;

    }




}
