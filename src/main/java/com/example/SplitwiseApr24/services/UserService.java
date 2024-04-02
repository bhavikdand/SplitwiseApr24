package com.example.SplitwiseApr24.services;

import com.example.SplitwiseApr24.exceptions.RegisterUserException;
import com.example.SplitwiseApr24.models.User;

public interface UserService {

    public User registerUser(String userName, String password, String phoneNumber) throws RegisterUserException;
}
