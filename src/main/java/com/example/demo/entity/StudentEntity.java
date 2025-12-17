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

    public StudentEntity(Long id,String name,String email,float cgpa){
        this.id = id;
        this.name = name;
        this.email = email;
        this.cgpa = cgpa;
    }

    public  void setName(String name){
        this.name = name;
    } 
    public String getName(){ z
        return this.name;
    }
    public void setCgpa(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setCgpa(float cgpa){
        this.cgpa = cgpa;
    }
    public float getCgpa(){
        return this.cgpa;
    }
}