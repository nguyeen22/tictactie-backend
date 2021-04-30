package com.example.service;

import com.example.entity.Player;
import org.hibernate.service.spi.ServiceException;

public interface IAuthenticationService {
    Player login(String userName) throws ServiceException;
}
