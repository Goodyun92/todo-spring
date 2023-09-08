package com.example.todo.model.dto;

import lombok.Data;

@Data
public class PlanDto {
    private String date;    //YYMMDD
    private String content;
    private Boolean checkStatus;
    private String review;
}
