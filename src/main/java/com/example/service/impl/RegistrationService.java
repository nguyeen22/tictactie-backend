package com.example.service.impl;

import com.example.dto.RegistrationDTO;
import com.example.entity.Player;
import com.example.security.UserNameValidator;
import com.example.security.token.ConfirmationToken;
import com.example.security.token.ConfirmationTokenService;
import com.example.service.IRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService implements IRegistrationService {
    private final UserDetailsServiceImpl playerDetailsService;
    private final UserNameValidator userNameValidator;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public String register(RegistrationDTO registrationDTO) {
        boolean isValidUserName = userNameValidator.test(registrationDTO.getUserName());
        if (!isValidUserName) {
            throw new IllegalStateException("Username not valid");
        }
        return playerDetailsService.signUpUser(
                new Player(
                        registrationDTO.getUserName(),
                        registrationDTO.getPassword(),
                        registrationDTO.getEmail()
                )
        );
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmesAt(token);
        playerDetailsService.enablePlayerAccount(
                confirmationToken.getPlayer().getUsername());
        return "confirmed";
    }
}
