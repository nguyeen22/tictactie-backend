package com.example.service.impl;


import com.example.controller.GameController;
import com.example.entity.Player;
import com.example.repository.PlayerRepository;
import com.example.service.IAuthenticationService;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final PlayerRepository playerRepository;
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @Override
    public Player login(String userName) throws ServiceException {
        Player player = null;
        try {
            player = playerRepository.findOneByUserName(userName);
        } catch (Exception e) {
            logger.error("Error Searching User By UserName or PhoneNumber of Email {}", e.toString());
        }
        if (player != null) {
            logger.debug("User Found !");
            return player;
        } else {
            logger.warn("User Not Found");
            return null;
        }
    }
}
