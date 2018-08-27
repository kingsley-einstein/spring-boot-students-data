package com.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    
    java.util.List<Student> findByAge(Integer age);
    Student findByFirstNameAndLastName(String firstName, String lastName);
}