package com.example.megha.controllers;

import com.example.megha.Response;
import com.example.megha.domains.Course;
import com.example.megha.domains.Student;
import com.example.megha.services.CourseService;
import com.example.megha.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {
    private final StudentService service;

    @GetMapping("/index")
    public String addStudent() {
        return "student_new";
    }

    @ResponseBody
    @PostMapping("/studentList")
    public Response ListAllStudent() {
        String message = null;
        boolean success = false;
        List<Student> students = null;
        try {
            students = service.listAll();
            success = true;
            message = "Successfully fetched course data";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(students, message, success);
    }
    @ResponseBody
    @PostMapping("/edit")
    public  Response editStudent(
            @RequestParam Long id
    ){
        String message = null;
        boolean success= true;
        Student student = new Student();
        try{
            student= service.findStudent(id);
            success = true;
            message =  "Successfully fetch student by Id";
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new Response(student, message, success);
    }

    @ResponseBody
    @PostMapping("/add")
    public Response saveOrUpdateStudent (
            @ModelAttribute("course") Student std
    ){
        Student students = null;
        String message = "";
        boolean success =false;
        Student student = null;
        try {
            if(std.getId() != null) {
                student = service.findStudent(std.getId());
                student.setStudentId(std.getStudentId());
                student.setStudentName(std.getStudentName());
                student.setMarks(std.getMarks());

            } else {
                student = new Student();
                student.setId(std.getId());
                student.setStudentId(std.getStudentId());
                student.setStudentName(std.getStudentName());
                student.setMarks(std.getMarks());
            }
            student= service.saveStudent(std);
            message ="Successfully Save Student";
            success = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Response(student, message, success );
    }

    @ResponseBody
    @PostMapping("/delete")
    public Response deleteStudent(
            @RequestParam Long id
    ){
        String message = null;
        boolean success = false;
        try{
            service.delete(id);
            success = true;
            message = "Successfully Deleted";
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(null, message, success);
    }
}

