package com.example.SplitwiseApr24.strategies.settle_up;

import com.example.SplitwiseApr24.models.Transaction;
import com.example.SplitwiseApr24.models.User;

import java.util.List;
import java.util.Map;

public interface SettleUpStrategy {

    List<Transaction> settleUp(Map<User, Double> userTotal);
}
