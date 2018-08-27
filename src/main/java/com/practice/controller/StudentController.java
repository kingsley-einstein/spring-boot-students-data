package com.practice.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.practice.repository.StudentRepository;
import com.practice.model.Student;
import com.practice.exceptions.RecordNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
public class StudentController {
    
    @Autowired
    StudentRepository studentRepository;
    
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    @ResponseBody
    public List<Student> getStudents() {
        if (studentRepository.findAll().size() <= 0) {
            throw new RecordNotFoundException();
        }
        return studentRepository.findAll();
    }
    
    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Student> getStudent(@PathVariable("id") String id) {
        if (studentRepository.findById(Integer.parseInt(id)) == null) {
            throw new RecordNotFoundException();
        }
        return studentRepository.findById(Integer.parseInt(id));
    }
    
    @RequestMapping(value = "/students", method = RequestMethod.POST)
    @ResponseBody
    public Student create(@RequestBody Map<String, String> body) {
        return studentRepository.save(new Student(body.get("firstname"), body.get("lastname"), Integer.parseInt(body.get("age"))));
    }
    
    @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Student update(@PathVariable("id") String id, @RequestBody Map<String, String> body) {
        Optional<Student> student = studentRepository.findById(Integer.parseInt(id));
        Student s = student.get();
        s.setFirstName(body.get("firstname"));
        s.setLastName(body.get("lastname"));
        s.setAge(Integer.parseInt(body.get("age")));
        return studentRepository.save(s);
    }
    
    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        studentRepository.deleteById(Integer.parseInt(id));
    }
    
    @RequestMapping(value = "/students/age/{age}", method = RequestMethod.GET)
    @ResponseBody
    public List<Student> getStudentsByAge(@PathVariable("age")String age) {
        return studentRepository.findByAge(Integer.parseInt(age));
    }
    
    @RequestMapping(value = "/students/name", method = RequestMethod.POST)
    @ResponseBody
    public Student getStudentByName(@RequestBody Map<String, String> body) {
        return studentRepository.findByFirstNameAndLastName(body.get("firstname"), body.get("lastname"));
    }
}