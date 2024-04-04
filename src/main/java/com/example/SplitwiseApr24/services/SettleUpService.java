package com.example.SplitwiseApr24.services;

import com.example.SplitwiseApr24.exceptions.InvalidRequestException;
import com.example.SplitwiseApr24.models.Transaction;

import java.util.List;

public interface SettleUpService {

    List<Transaction> settleGroup(int groupId) throws InvalidRequestException;

    List<Transaction> settleUser(int userId) throws InvalidRequestException;
}
