package com.studentmanagement.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents an Academic Record for a student in a specific subject.
 * Demonstrates OOP principles: Encapsulation, Business Logic
 */
public class AcademicRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String studentId;
    private String subject;
    private int semester;
    private double marks;
    private String grade;
    
    public AcademicRecord(String studentId, String subject, int semester, double marks) {
        this.studentId = studentId;
        this.subject = subject;
        this.semester = semester;
        // Clamp marks to the valid 0-100 range
        this.marks = Math.max(0, Math.min(100, marks));
        this.grade = calculateGrade(this.marks);
    }
    
    /**
     * Calculate grade based on marks
     * Demonstrates encapsulation of business logic
     */
    private String calculateGrade(double marks) {
        if (marks >= 90) return "A+";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B+";
        else if (marks >= 60) return "B";
        else if (marks >= 50) return "C";
        else if (marks >= 40) return "D";
        else return "F";
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public int getSemester() {
        return semester;
    }
    
    public void setSemester(int semester) {
        this.semester = semester;
    }
    
    public double getMarks() {
        return marks;
    }
    
    /**
     * Set marks, clamped to the valid range of 0-100.
     */
    public void setMarks(double marks) {
        this.marks = Math.max(0, Math.min(100, marks));
        this.grade = calculateGrade(this.marks);
    }
    
    public String getGrade() {
        return grade;
    }
    
    public boolean isPassed() {
        return marks >= 40;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicRecord that = (AcademicRecord) o;
        return semester == that.semester &&
               Objects.equals(studentId, that.studentId) &&
               Objects.equals(subject, that.subject);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(studentId, subject, semester);
    }
    
    @Override
    public String toString() {
        return "AcademicRecord{" +
                "StudentID='" + studentId + '\'' +
                ", Subject='" + subject + '\'' +
                ", Semester=" + semester +
                ", Marks=" + marks +
                ", Grade='" + grade + '\'' +
                ", Status=" + (isPassed() ? "PASS" : "FAIL") +
                '}';
    }
}
