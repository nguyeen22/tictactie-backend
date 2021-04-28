package com.example.service.impl;

import com.example.converter.GameConverter;
import com.example.entity.Player;
import com.example.repository.GameRepository;
import com.example.repository.PlayerRepository;
import com.example.security.ContextUser;
import com.example.service.IPlayerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerService implements IPlayerService {
    private final PlayerRepository playerRepository;
    private final GameConverter gameConverter;
    private final GameRepository gameRepository;

    public Player getLoggedUser() {
        ContextUser principal = (ContextUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return playerRepository.findOneByUserName(principal.getPlayer().getUsername());
    }

    @Override
    public Player fetchPlayerByUserNameAndPassword(String email, String password) {
        return playerRepository.findByUserNameAndPassword(email, password);
    }

}
