package com.example.megha.domains;

import lombok.ToString;

import javax.persistence.*;


@Entity
@ToString
@Table(name="tbl_course")

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "course_name")
    private String courseName;
    @Column(name = "course_marks")
    private int courseMarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseMarks() {
        return courseMarks;
    }

    public void setCourseMarks(int courseMarks) {
        this.courseMarks = courseMarks;
    }

    public Course(Long id, int courseId, String courseName, int courseMarks) {
        this.id = id;
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseMarks = courseMarks;
    }

    public Course() {
    }
}

