package com.example.demo.entity;    
import jakarta.persistence.entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity

public class StudentEntity{
    @Td
    @GeneratedValue(strategy = GeneratedType.IDENTITY ) 
     
    private Long id;
    private String name;    
    private String email;
    private float cgpa;

    public  void setName(String name){
        this.name = name;
    } 
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public void setC
}