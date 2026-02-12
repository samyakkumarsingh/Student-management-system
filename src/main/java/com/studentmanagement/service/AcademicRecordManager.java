package com.studentmanagement.service;

import com.studentmanagement.model.AcademicRecord;
import com.studentmanagement.util.DataStorage;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages Academic Records operations.
 * Demonstrates OOP principles: Single Responsibility, Modularity
 */
public class AcademicRecordManager {
    private List<AcademicRecord> academicRecords;
    private DataStorage dataStorage;
    
    public AcademicRecordManager() {
        this.academicRecords = new ArrayList<>();
        this.dataStorage = new DataStorage();
        loadAcademicRecords();
    }
    
    /**
     * Add an academic record
     */
    public boolean addRecord(AcademicRecord record) {
        // Remove existing record if any for the same student, subject, and semester
        academicRecords.removeIf(r -> 
            r.getStudentId().equals(record.getStudentId()) &&
            r.getSubject().equals(record.getSubject()) &&
            r.getSemester() == record.getSemester()
        );
        academicRecords.add(record);
        saveAcademicRecords();
        return true;
    }
    
    /**
     * Get all academic records for a student
     */
    public List<AcademicRecord> getStudentRecords(String studentId) {
        return academicRecords.stream()
                .filter(r -> r.getStudentId().equals(studentId))
                .sorted(Comparator.comparing(AcademicRecord::getSemester))
                .collect(Collectors.toList());
    }
    
    /**
     * Get records for a specific semester
     */
    public List<AcademicRecord> getRecordsBySemester(String studentId, int semester) {
        return academicRecords.stream()
                .filter(r -> r.getStudentId().equals(studentId) && 
                           r.getSemester() == semester)
                .collect(Collectors.toList());
    }
    
    /**
     * Get records for a specific subject
     */
    public List<AcademicRecord> getRecordsBySubject(String studentId, String subject) {
        return academicRecords.stream()
                .filter(r -> r.getStudentId().equals(studentId) && 
                           r.getSubject().equalsIgnoreCase(subject))
                .collect(Collectors.toList());
    }
    
    /**
     * Calculate overall percentage for a student
     */
    public double calculateOverallPercentage(String studentId) {
        List<AcademicRecord> records = getStudentRecords(studentId);
        if (records.isEmpty()) {
            return 0.0;
        }
        
        double totalMarks = records.stream()
                .mapToDouble(AcademicRecord::getMarks)
                .sum();
        
        return totalMarks / records.size();
    }
    
    /**
     * Calculate semester percentage
     */
    public double calculateSemesterPercentage(String studentId, int semester) {
        List<AcademicRecord> records = getRecordsBySemester(studentId, semester);
        if (records.isEmpty()) {
            return 0.0;
        }
        
        double totalMarks = records.stream()
                .mapToDouble(AcademicRecord::getMarks)
                .sum();
        
        return totalMarks / records.size();
    }
    
    /**
     * Get overall grade based on percentage
     */
    public String calculateOverallGrade(String studentId) {
        double percentage = calculateOverallPercentage(studentId);
        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B+";
        else if (percentage >= 60) return "B";
        else if (percentage >= 50) return "C";
        else if (percentage >= 40) return "D";
        else return "F";
    }
    
    /**
     * Check if student has passed all subjects
     */
    public boolean hasPassedAllSubjects(String studentId) {
        List<AcademicRecord> records = getStudentRecords(studentId);
        return !records.isEmpty() && records.stream().allMatch(AcademicRecord::isPassed);
    }
    
    /**
     * Get all academic records
     */
    public List<AcademicRecord> getAllRecords() {
        return new ArrayList<>(academicRecords);
    }
    
    private void loadAcademicRecords() {
        this.academicRecords = dataStorage.loadAcademicRecords();
    }
    
    private void saveAcademicRecords() {
        dataStorage.saveAcademicRecords(academicRecords);
    }
}
