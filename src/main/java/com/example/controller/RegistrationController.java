package com.example.controller;

import com.example.dto.RegistrationDTO;
import com.example.service.IPlayerService;
import com.example.service.IRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {
    private final IRegistrationService registrationService;
    private final IPlayerService playerService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @PostMapping
    public String register(@RequestBody RegistrationDTO registrationDTO) {
        return registrationService.register(registrationDTO);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }


}
