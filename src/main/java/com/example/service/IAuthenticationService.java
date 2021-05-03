package com.example.service;

import com.example.entity.Player;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAuthenticationService {
    Player login(String userName) throws ServiceException;
    void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication);
}
