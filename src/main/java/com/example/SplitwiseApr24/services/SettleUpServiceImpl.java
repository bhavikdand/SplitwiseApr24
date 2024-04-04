package com.example.SplitwiseApr24.services;

import com.example.SplitwiseApr24.exceptions.InvalidRequestException;
import com.example.SplitwiseApr24.models.*;
import com.example.SplitwiseApr24.repositories.GroupExpenseRepository;
import com.example.SplitwiseApr24.repositories.GroupRepository;
import com.example.SplitwiseApr24.strategies.settle_up.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SettleUpServiceImpl implements SettleUpService{

    private GroupRepository groupRepository;
    private GroupExpenseRepository groupExpenseRepository;
    private SettleUpStrategy settleUpStrategy;

    @Autowired
    public SettleUpServiceImpl(GroupRepository groupRepository, GroupExpenseRepository groupExpenseRepository, SettleUpStrategy settleUpStrategy) {
        this.groupRepository = groupRepository;
        this.groupExpenseRepository = groupExpenseRepository;
        this.settleUpStrategy = settleUpStrategy;
    }

    @Override
    public List<Transaction> settleGroup(int groupId) throws InvalidRequestException {
        /*
        Validate the group id using DB
        Fetch list of expenses from group expense table using group id
        Condense all the expenses down to user and their final total
        Use strategy to actually come up with list of transactions
         */
        Group group =  this.groupRepository.findById(groupId).orElseThrow(() -> new InvalidRequestException("Invalid group id"));
        List<GroupExpense> groupExpenses = this.groupExpenseRepository.findAllByGroupId(groupId);
        List<Expense> expenses = groupExpenses.stream().map(GroupExpense::getExpense).toList();

        Map<User, Double> userTotal = new HashMap<>();
        for(Expense exp: expenses){
            for (ExpenseUser expenseUser : exp.getExpenseUsers()) {
                userTotal.put(expenseUser.getUser(), userTotal.getOrDefault(expenseUser.getUser(), 0D) +
                        ((expenseUser.getExpenseType().equals(ExpenseType.PAID) ? 1 : -1)) * expenseUser.getAmount());
            }
        }

        return settleUpStrategy.settleUp(userTotal);
    }

    @Override
    public List<Transaction> settleUser(int userId) throws InvalidRequestException {
        /*
        Validate the user with help of DB
        Query the expense user table to get list of expenses that the user was part of
        Condense all the expenses down to user and their final total (Note: Don't duplicate this logic)
        Use strategy to actually come up with list of transactions
         */



        return null;
    }


}
