package com.example.service;

import com.example.dto.RegistrationDTO;


public interface  IRegistrationService {
    String register(RegistrationDTO registratrationDTO);
    String confirmToken(String token);
}
