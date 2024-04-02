package com.example.SplitwiseApr24.commands;

import com.example.SplitwiseApr24.controllers.UserController;
import com.example.SplitwiseApr24.dtos.RegisterUserRequestDto;
import com.example.SplitwiseApr24.dtos.RegisterUserResponseDto;
import com.example.SplitwiseApr24.dtos.ResponseType;
import com.example.SplitwiseApr24.exceptions.InvalidCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserCommand implements Command{

    private static final String REGISTER_USER_KEY = "Register";

    UserController userController;

    @Autowired
    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
        CommandRegistry.getInstance().addCommand(REGISTER_USER_KEY, this);
    }

    @Override
    public void execute(String input) throws InvalidCommandException {
        // eg input: Register vinsmokesanji 003 namisswwaann
        String[] s = input.split(" ");
        if(s.length != 4){
            throw new InvalidCommandException("Register user command is not in correct format");
        }
        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName(s[1]);
        requestDto.setPassword(s[3]);
        requestDto.setPhoneNumber(s[2]);
        RegisterUserResponseDto responseDto = userController.registerUser(requestDto);
        if(responseDto.getResponse().getType().equals(ResponseType.FAILURE)){
            System.out.println("Err:" + responseDto.getResponse().getErrorMessage());
        } else {
            System.out.println(responseDto.getUser());
        }
    }
}
