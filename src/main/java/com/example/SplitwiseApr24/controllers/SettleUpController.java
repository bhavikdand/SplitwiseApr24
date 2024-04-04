package com.example.SplitwiseApr24.controllers;

import com.example.SplitwiseApr24.dtos.Response;
import com.example.SplitwiseApr24.dtos.SettleGroupRequestDto;
import com.example.SplitwiseApr24.dtos.SettleGroupResponseDto;
import com.example.SplitwiseApr24.exceptions.InvalidRequestException;
import com.example.SplitwiseApr24.models.Transaction;
import com.example.SplitwiseApr24.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {

    private SettleUpService settleUpService;

    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleGroupResponseDto settleGroup(SettleGroupRequestDto requestDto){
        SettleGroupResponseDto responseDto = new SettleGroupResponseDto();
        try{
            if(requestDto.getGroupId() <= 0){
                throw new InvalidRequestException("Invalid group id");
            }
            List<Transaction> transactions = this.settleUpService.settleGroup(requestDto.getGroupId());
            responseDto.setTransactions(transactions);
            responseDto.setResponse(Response.getSuccessResponse());
        }catch (Exception e){
            Response response = Response.getFailureResponse(e.getMessage());
            responseDto.setResponse(response);
        }
        return responseDto;
    }

    public void settleUser(){

    }
}
