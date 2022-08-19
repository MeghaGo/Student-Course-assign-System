package com.example.megha.controllers;

import com.example.megha.Response;
import com.example.megha.domains.Course;
import com.example.megha.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("course")
public class CourseController {
    private final CourseService service;

    @GetMapping("/index")
    public String addCourse() {
        return "course_new";
    }

    @ResponseBody
    @PostMapping("/courseList")
    public Response ListAllCourse() {
        String message = null;
        boolean success = false;
        List<Course> courses = null;
        try {
            courses = service.listAll();
            success = true;
            message = "Successfully fetched course data";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(courses, message, success);
    }
    @ResponseBody
    @PostMapping("/edit")
    public  Response editCourse(
            @RequestParam Long id
    ){
        String message = null;
        boolean success= true;
        Course course = new Course();
        try{
            course = service.findCourse(id);
            success = true;
            message =  "Successfully fetch course by Id";
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new Response(course, message, success);
    }

    @ResponseBody
    @PostMapping("/add")
    public Response saveOrUpdateCourse (
            @ModelAttribute("course") Course courseObj
    ){
        Course course = null;
        String message = "";
        boolean success =false;
        try {
            if(courseObj.getId() != null) {
                course = service.findCourse(courseObj.getId());
                course.setCourseId(courseObj.getCourseId());
                course.setCourseName(courseObj.getCourseName());
                course.setCourseMarks(courseObj.getCourseMarks());

            } else {
                course = new Course();
                course.setCourseId(courseObj.getCourseId());
                course.setCourseName(courseObj.getCourseName());
                course.setCourseMarks(courseObj.getCourseMarks());

            }
            System.out.println(courseObj);
            course= service.saveCourse(course);
            message ="Successfully Save Course";
            success = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Response(course, message, success );
    }

    @ResponseBody
    @PostMapping("/delete")
    public Response deleteCourse(
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
