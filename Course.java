package com.example.demo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Course {
    Integer id;
    String name;
    String duration;
    LocalDate date;
    LocalDate startingDate;
    Integer entries;
    Boolean active;


}
