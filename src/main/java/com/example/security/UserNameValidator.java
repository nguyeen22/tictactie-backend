package com.example.security;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class UserNameValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return true;
    }
}
