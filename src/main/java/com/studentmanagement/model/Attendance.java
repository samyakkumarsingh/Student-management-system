package com.studentmanagement.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents an Attendance record for a student.
 * Demonstrates OOP principles: Encapsulation
 */
public class Attendance implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String studentId;
    private LocalDate date;
    private AttendanceStatus status;
    private String subject;
    
    public enum AttendanceStatus {
        PRESENT, ABSENT, LATE, EXCUSED
    }
    
    public Attendance(String studentId, LocalDate date, AttendanceStatus status, String subject) {
        this.studentId = studentId;
        this.date = date;
        this.status = status;
        this.subject = subject;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public AttendanceStatus getStatus() {
        return status;
    }
    
    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(date, that.date) &&
               Objects.equals(subject, that.subject);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(studentId, date, subject);
    }
    
    @Override
    public String toString() {
        return "Attendance{" +
                "StudentID='" + studentId + '\'' +
                ", Date=" + date +
                ", Status=" + status +
                ", Subject='" + subject + '\'' +
                '}';
    }
}
