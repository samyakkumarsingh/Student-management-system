package com.studentmanagement.service;

import com.studentmanagement.model.Attendance;
import com.studentmanagement.model.Attendance.AttendanceStatus;
import com.studentmanagement.util.DataStorage;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages Attendance operations.
 * Demonstrates OOP principles: Single Responsibility, Modularity
 */
public class AttendanceManager {
    private List<Attendance> attendanceRecords;
    private DataStorage dataStorage;
    
    public AttendanceManager() {
        this.attendanceRecords = new ArrayList<>();
        this.dataStorage = new DataStorage();
        loadAttendance();
    }
    
    /**
     * Record attendance for a student
     */
    public boolean recordAttendance(Attendance attendance) {
        // Remove existing record if any for the same student, date, and subject
        attendanceRecords.removeIf(a -> 
            a.getStudentId().equals(attendance.getStudentId()) &&
            a.getDate().equals(attendance.getDate()) &&
            a.getSubject().equals(attendance.getSubject())
        );
        attendanceRecords.add(attendance);
        saveAttendance();
        return true;
    }
    
    /**
     * Get attendance records for a specific student
     */
    public List<Attendance> getStudentAttendance(String studentId) {
        return attendanceRecords.stream()
                .filter(a -> a.getStudentId().equals(studentId))
                .sorted(Comparator.comparing(Attendance::getDate).reversed())
                .collect(Collectors.toList());
    }
    
    /**
     * Get attendance records for a specific date
     */
    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRecords.stream()
                .filter(a -> a.getDate().equals(date))
                .collect(Collectors.toList());
    }
    
    /**
     * Get attendance records for a subject
     */
    public List<Attendance> getAttendanceBySubject(String subject) {
        return attendanceRecords.stream()
                .filter(a -> a.getSubject().equalsIgnoreCase(subject))
                .collect(Collectors.toList());
    }
    
    /**
     * Calculate attendance percentage for a student
     */
    public double calculateAttendancePercentage(String studentId) {
        List<Attendance> studentRecords = getStudentAttendance(studentId);
        if (studentRecords.isEmpty()) {
            return 0.0;
        }
        
        long presentCount = studentRecords.stream()
                .filter(a -> a.getStatus() == AttendanceStatus.PRESENT || 
                           a.getStatus() == AttendanceStatus.LATE)
                .count();
        
        return (presentCount * 100.0) / studentRecords.size();
    }
    
    /**
     * Calculate attendance percentage for a student in a specific subject
     */
    public double calculateSubjectAttendancePercentage(String studentId, String subject) {
        List<Attendance> records = attendanceRecords.stream()
                .filter(a -> a.getStudentId().equals(studentId) && 
                           a.getSubject().equalsIgnoreCase(subject))
                .collect(Collectors.toList());
        
        if (records.isEmpty()) {
            return 0.0;
        }
        
        long presentCount = records.stream()
                .filter(a -> a.getStatus() == AttendanceStatus.PRESENT || 
                           a.getStatus() == AttendanceStatus.LATE)
                .count();
        
        return (presentCount * 100.0) / records.size();
    }
    
    /**
     * Get all attendance records
     */
    public List<Attendance> getAllAttendance() {
        return new ArrayList<>(attendanceRecords);
    }
    
    private void loadAttendance() {
        this.attendanceRecords = dataStorage.loadAttendance();
    }
    
    private void saveAttendance() {
        dataStorage.saveAttendance(attendanceRecords);
    }
}
