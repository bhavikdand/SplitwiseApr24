package com.example.SplitwiseApr24.dtos;

import com.example.SplitwiseApr24.models.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class SettleGroupResponseDto {
    private Response response;

    private List<Transaction> transactions;
}
