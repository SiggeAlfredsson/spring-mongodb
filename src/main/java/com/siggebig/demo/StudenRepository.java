package com.siggebig.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudenRepository extends MongoRepository<Student, String> {

    Optional<Student> findStudentByEmail(String email);

}
