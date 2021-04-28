package com.example.service;

import com.example.entity.Player;

public interface IPlayerService {
    Player getLoggedUser();
    Player fetchPlayerByUserNameAndPassword(String email, String password);


}
