package com.example.SplitwiseApr24.dtos;

import lombok.Data;

@Data
public class Response {
    private String errorMessage;
    private ResponseType type;

    public static Response getSuccessResponse(){
        Response response = new Response();
        response.setType(ResponseType.SUCCESS);
        return response;
    }

    public static Response getFailureResponse(String errorMessage){
        Response response = new Response();
        response.setType(ResponseType.FAILURE);
        response.setErrorMessage(errorMessage);
        return response;
    }

}
