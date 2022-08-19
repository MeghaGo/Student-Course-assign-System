package com.example.megha.services;

import com.example.megha.domains.Course;
import com.example.megha.domains.Student;
import com.example.megha.repositories.CourseRepository;
import com.example.megha.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> listAll() {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    public Student saveStudent(Student student) {
        Student students = studentRepository.save(student);
        return students;

    }

    public Student findStudent(long id) {
        Student students = studentRepository.findById(id).get();
        return students;
    }

    public void delete(long id) {
        studentRepository.deleteById(id);
    }
}

