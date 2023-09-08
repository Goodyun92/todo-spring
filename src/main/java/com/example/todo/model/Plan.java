package com.example.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;
    private String date;    //YYMMDD
    private String content;
    private Boolean checkStatus;
    private String review;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}

