package com.example.demo.entity;    
import jakarta.persistence.entity;

@Entity

public class StudentEntity{
    @Td
    @GeneratedValue(strategy = GeneratedType.IDENTITY ) 
     
    private Long id;
    private String name;    
    private String email;
    private float cgpa;
}