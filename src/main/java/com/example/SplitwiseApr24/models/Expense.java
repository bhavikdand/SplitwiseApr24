package com.example.SplitwiseApr24.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Expense extends BaseModel{
    private double amount;
    private Date addedAt;
    private String description;
    private String proofUrl;

    @Enumerated(value = EnumType.ORDINAL)
    private Currency currency;

    @OneToMany(mappedBy = "expense")
    private List<ExpenseUser> expenseUsers;
}
