package com.example.SplitwiseApr24.services;

import com.example.SplitwiseApr24.exceptions.InvalidRequestException;
import com.example.SplitwiseApr24.exceptions.RegisterUserException;
import com.example.SplitwiseApr24.models.User;
import com.example.SplitwiseApr24.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
        user.setPhoneNumber(phoneNumber);
        return this.userRepository.save(user);
    }

    public void login(String userName, String password) throws InvalidRequestException {
        User user = this.userRepository.findUserByUserName(userName).orElseThrow(() -> new InvalidRequestException("Invalid user"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(password, user.getPassword())){
            // login the user
        } else {
            // throw exception
        }
    }


}
