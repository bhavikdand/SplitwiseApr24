package com.example.SplitwiseApr24.services;

import com.example.SplitwiseApr24.exceptions.RegisterUserException;
import com.example.SplitwiseApr24.models.User;
import com.example.SplitwiseApr24.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String userName, String password, String phoneNumber) throws RegisterUserException {
        Optional<User> exitingUserCheck = this.userRepository.findUserByUserNameEqualsOrPhoneNumber(userName, phoneNumber);
        if(exitingUserCheck.isPresent()){
            throw new RegisterUserException("User already registered!");
        }
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        return this.userRepository.save(user);
    }
}
