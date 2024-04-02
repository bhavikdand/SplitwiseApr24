package com.example.SplitwiseApr24.dtos;

import lombok.Data;

@Data
public class RegisterUserRequestDto {
    private String userName;
    private String password;
    private String phoneNumber;
}
