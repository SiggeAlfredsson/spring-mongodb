package com.siggebig.demo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudenRepository studenRepository;

    public List<Student> getAllStudents() {
        return studenRepository.findAll();
    }
}
