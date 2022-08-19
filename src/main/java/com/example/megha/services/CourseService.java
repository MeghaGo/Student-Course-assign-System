package com.example.megha.services;

import com.example.megha.domains.Course;
import com.example.megha.repositories.CourseRepository;
import com.example.megha.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> listAll() {
        List<Course> courses = courseRepository.findAll();
        return courses;
    }

    public Course saveCourse(Course course) {
        Course courses = courseRepository.save(course);
        return course;

    }

    public Course findCourse(long id) {
        Course courses = courseRepository.findById(id).get();
        return courses;
    }

    public void delete(long id) {
        courseRepository.deleteById(id);
    }
}