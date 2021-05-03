package com.example.controller;


import com.example.dto.LoginDTO;
import com.example.dto.ResponseData;
import com.example.entity.Player;
import com.example.service.IAuthenticationService;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final IAuthenticationService authenticationService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseData login(@RequestBody LoginDTO requestLoginDTO, HttpServletResponse p_HttpServletResponse, HttpServletRequest p_Request) {
        ResponseData result = new ResponseData("500", "Login Failed");
        if (requestLoginDTO.getPassword() != null && requestLoginDTO.getUserName() != null) {
            Player playerDetails = null;
            try {
                playerDetails = authenticationService.login(requestLoginDTO.getUserName());
            } catch (ServiceException e) {
                logger.error("Error Login {}", e.toString());
            }
            if (playerDetails != null) {
                Authentication authentication = daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(requestLoginDTO.getUserName(), requestLoginDTO.getPassword()));
                if (authentication.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.info("Login Successful as " + playerDetails.getUsername());
                    result = new ResponseData("200", "Login Success");
                }
            }
            else {
                logger.info("User Not Found");
                result = new ResponseData("500", "User Not Exist");
            }
        }
        else {
            logger.error("Missing Login Body Request");
        }
        return result;
    }

    @PostMapping("/logout")
    public ResponseData logout(HttpServletRequest p_Request, HttpServletResponse p_Response) {
        ResponseData result = new ResponseData("500", "Logout Failed");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            authenticationService.logout(p_Request, p_Response, auth);
            logger.info("Logout Successful");
            result = new ResponseData("200", "Logout Success");
        }else {
            logger.error("Logout Failed");
        }
        return result;
    }



}