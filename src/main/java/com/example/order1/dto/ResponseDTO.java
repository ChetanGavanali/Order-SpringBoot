package com.example.order1.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDTO {
    private String message;
    private Object object;

    public ResponseDTO(String string, String response) {
        this.message = string;
        this.object = response;
    }


    public ResponseDTO(String string, Object response) {
        this.message = string;
        this.object = response;
    }


}