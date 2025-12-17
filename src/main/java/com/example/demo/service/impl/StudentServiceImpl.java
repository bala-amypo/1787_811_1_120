package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StudentEntity;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository repo;

    @Override
    public List<StudentEntity> getStudents(){
        return ser.findAll();
    }
    @Override
    public StudentEntity addStudents(StudentEntity student) {
        return repo.save(student);
    }

}   