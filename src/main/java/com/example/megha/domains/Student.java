package com.example.megha.domains;

import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString
@Table(name="tbl_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "student_name")
    private String studentName;

    private int marks;

    @OneToMany
    @JoinTable
            (name="tbl_student_course",
    joinColumns ={ @JoinColumn(name = "student_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")})
    private List<Course> course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Student(Long id, int studentId, String studentName, int marks) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.marks = marks;
    }

    public Student() {
    }
}

