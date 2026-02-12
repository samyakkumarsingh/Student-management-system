package com.studentmanagement.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a Student in the system.
 * Demonstrates OOP principles: Encapsulation, Data Abstraction
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String studentId;
    private String name;
    private String email;
    private String phoneNumber;
    private String department;
    private int semester;
    
    public Student(String studentId, String name, String email, String phoneNumber, 
                   String department, int semester) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.semester = semester;
    }
    
    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public int getSemester() {
        return semester;
    }
    
    /**
     * Set the semester, enforcing a minimum value of 1.
     */
    public void setSemester(int semester) {
        this.semester = Math.max(1, semester);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "ID='" + studentId + '\'' +
                ", Name='" + name + '\'' +
                ", Email='" + email + '\'' +
                ", Phone='" + phoneNumber + '\'' +
                ", Department='" + department + '\'' +
                ", Semester=" + semester +
                '}';
    }
}
