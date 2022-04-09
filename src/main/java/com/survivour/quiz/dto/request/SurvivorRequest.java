package com.survivour.quiz.dto.request;


import lombok.Data;

@Data
public class SurvivorRequest {
    private long id;
    private String name;
    private String age;
    private String gender;
    private String location;
    private boolean isInfected;
    private String resource;
}
