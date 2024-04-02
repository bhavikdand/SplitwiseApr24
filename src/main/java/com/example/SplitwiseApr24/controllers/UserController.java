package com.example.SplitwiseApr24.controllers;

import com.example.SplitwiseApr24.dtos.RegisterUserRequestDto;
import com.example.SplitwiseApr24.dtos.RegisterUserResponseDto;
import com.example.SplitwiseApr24.dtos.Response;
import com.example.SplitwiseApr24.exceptions.InvalidRequestException;
import com.example.SplitwiseApr24.models.User;
import com.example.SplitwiseApr24.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public RegisterUserResponseDto registerUser(RegisterUserRequestDto requestDto){
        RegisterUserResponseDto registerUserResponseDto = new RegisterUserResponseDto();
        try {
            validateRequest(requestDto);
            User user = this.userService.registerUser(requestDto.getUserName(), requestDto.getPassword(), requestDto.getPhoneNumber());
            registerUserResponseDto.setUser(user);
            registerUserResponseDto.setResponse(Response.getSuccessResponse());
        }catch (Exception e){
            registerUserResponseDto.setResponse(Response.getFailureResponse(e.getMessage()));
        }
        return registerUserResponseDto;
    }

    private void validateRequest(RegisterUserRequestDto requestDto) throws InvalidRequestException {
        if(requestDto.getUserName() == null || requestDto.getPassword() == null || requestDto.getPhoneNumber() == null){
            throw new InvalidRequestException("Username or password or phone number cannot be null");
        }
        //TODO add some validations on password

    }
}
