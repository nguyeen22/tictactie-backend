package com.example.controller;

import com.example.entity.Player;
import com.example.service.IPlayerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class PlayerController {
    private final IPlayerService playerService;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @PostMapping("login")
    public Player loginUser(@RequestBody Player user) {
        String tempUserMame = user.getUsername();
       String tempPassword = bCryptPasswordEncoder.encode(user.getPassword());
     Player playerObj = null;
       if(tempPassword != null && tempUserMame != null){
            playerObj = playerService.fetchPlayerByUserNameAndPassword(tempUserMame, tempPassword);
       }
       if (playerObj == null) {
           throw new IllegalStateException("Bad Credentials");
     }
       return playerObj;
   }

}
