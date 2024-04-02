package com.example.SplitwiseApr24.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class User extends BaseModel{

    private String userName;
    private String password;
    private String phoneNumber;

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
