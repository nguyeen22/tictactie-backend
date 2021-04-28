package com.example.service.impl;


import com.example.entity.Player;
import com.example.repository.PlayerRepository;
import com.example.security.token.ConfirmationToken;
import com.example.security.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    public static final String USER_NOT_FOUND_MSG = "Username %s not found";
    private final PlayerRepository playerRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return playerRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

    public String signUpUser(Player player) {
        boolean userExists = playerRepository.findByUserName(player.getUsername()).isPresent();
        if (userExists) {
            throw new IllegalStateException("username already taken");
        }
        String encodePassword = bCryptPasswordEncoder.encode(player.getPassword());
        player.setPassword(encodePassword);
        playerRepository.save(player);
        String token = UUID.randomUUID().toString();
        //To do: send confirmation token
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                player
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return "Success\n" + token + "\nStatus: " + HttpStatus.OK;
    }

    public int enablePlayerAccount(String username) {
        return playerRepository.enablePlayerAccount(username);
    }
}
