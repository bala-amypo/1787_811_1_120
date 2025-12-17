package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.StudentEntity;
import com.example.demo.service.StudentService;


@RestController
public class StudentController {

    @Autowired
    StudentService ser;

    @PostMapping("/addStudents")
    public StudentEntity addStudents(@RequestBody StudentEntity student){
        return ser.addStudents(student);
    }
    
}