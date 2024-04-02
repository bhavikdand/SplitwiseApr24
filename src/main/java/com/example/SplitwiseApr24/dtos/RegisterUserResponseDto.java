package com.example.SplitwiseApr24.dtos;

import com.example.SplitwiseApr24.models.User;
import lombok.Data;

@Data
public class RegisterUserResponseDto {
    private User user;
    private Response response;
}
