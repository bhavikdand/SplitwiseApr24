package com.example.SplitwiseApr24.strategies.settle_up;

import com.example.SplitwiseApr24.models.Transaction;
import com.example.SplitwiseApr24.models.User;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TwoHeapSettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> settleUp(Map<User, Double> userTotal) {
        PriorityQueue<Pair<User, Double>> maxHeap = new PriorityQueue<>((t1, t2) -> (int) (t1.getSecond() - t2.getSecond()));

        PriorityQueue<Pair<User, Double>> minHeap = new PriorityQueue<>((t1, t2) -> (int) (t2.getSecond() - t1.getSecond()));

        for (Map.Entry<User, Double> entry : userTotal.entrySet()) {
            if(entry.getValue() > 0){
                maxHeap.add(Pair.of(entry.getKey(), entry.getValue()));
            } else {
                minHeap.add(Pair.of(entry.getKey(), entry.getValue()));
            }
        }

        List<Transaction> transactions = new ArrayList<>();
        while (!minHeap.isEmpty() && !maxHeap.isEmpty()){
            Pair<User, Double> userToPay = minHeap.poll();
            Pair<User, Double> userToGetMoney = maxHeap.poll();

            double amountToBeTransferred = Math.min(Math.abs(userToPay.getSecond()), userToGetMoney.getSecond());
            Transaction transaction = new Transaction();
            transaction.setAmount(amountToBeTransferred);
            transaction.setPaidFrom(userToPay.getFirst());
            transaction.setPaidTo(userToGetMoney.getFirst());
            transactions.add(transaction);

            if(userToGetMoney.getSecond() - amountToBeTransferred > 0){
                maxHeap.add(Pair.of(userToGetMoney.getFirst(), userToGetMoney.getSecond() - amountToBeTransferred));
            }

            if(userToPay.getSecond() + amountToBeTransferred < 0){
                minHeap.add(Pair.of(userToPay.getFirst(), userToPay.getSecond() + amountToBeTransferred));
            }
        }



        return transactions;
    }
}
