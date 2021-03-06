package com.example.hamam.project.Model;

public class User {

    public String name;
    private String id;
    public String coursename;
    public String phone;
    public String fbacoune;
    public String attendence;
    public String marks;
    public String grade;
    public String comment;
    public  String password ;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(String name, String id, String coursename, String phone, String fbacoune, String attendence, String marks, String grade, String comment, String password) {
        this.name = name;
        this.id = id;
        this.coursename = coursename;
        this.phone = phone;
        this.fbacoune = fbacoune;
        this.attendence = attendence;
        this.marks = marks;
        this.grade = grade;
        this.comment = comment;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFbacoune() {
        return fbacoune;
    }

    public void setFbacoune(String fbacoune) {
        this.fbacoune = fbacoune;
    }

    public String getAttendence() {
        return attendence;
    }

    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
