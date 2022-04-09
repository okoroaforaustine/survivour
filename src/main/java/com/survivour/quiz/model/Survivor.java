package com.survivour.quiz.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Survivor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;
 private String name;
 private String age;
 private String gender;
 private String location;
 private boolean isInfected;
 private Resources resources;
}
