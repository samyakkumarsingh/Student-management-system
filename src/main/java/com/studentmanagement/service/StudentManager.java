package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import com.studentmanagement.util.DataStorage;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages Student operations (CRUD).
 * Demonstrates OOP principles: Single Responsibility, Modularity
 */
public class StudentManager {
    private Map<String, Student> students;
    private DataStorage dataStorage;
    
    public StudentManager() {
        this.students = new HashMap<>();
        this.dataStorage = new DataStorage();
        loadStudents();
    }
    
    /**
     * Add a new student to the system
     */
    public boolean addStudent(Student student) {
        if (students.containsKey(student.getStudentId())) {
            return false;
        }
        students.put(student.getStudentId(), student);
        saveStudents();
        return true;
    }
    
    /**
     * Get a student by ID
     */
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }
    
    /**
     * Update student information
     */
    public boolean updateStudent(Student student) {
        if (!students.containsKey(student.getStudentId())) {
            return false;
        }
        students.put(student.getStudentId(), student);
        saveStudents();
        return true;
    }
    
    /**
     * Delete a student from the system
     */
    public boolean deleteStudent(String studentId) {
        if (students.remove(studentId) != null) {
            saveStudents();
            return true;
        }
        return false;
    }
    
    /**
     * Get all students
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }
    
    /**
     * Search students by name
     */
    public List<Student> searchByName(String name) {
        return students.values().stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    /**
     * Get students by department
     */
    public List<Student> getStudentsByDepartment(String department) {
        return students.values().stream()
                .filter(s -> s.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }
    
    /**
     * Check if a student exists
     */
    public boolean studentExists(String studentId) {
        return students.containsKey(studentId);
    }
    
    /**
     * Get total number of students
     */
    public int getTotalStudents() {
        return students.size();
    }
    
    private void loadStudents() {
        List<Student> loadedStudents = dataStorage.loadStudents();
        for (Student student : loadedStudents) {
            students.put(student.getStudentId(), student);
        }
    }
    
    private void saveStudents() {
        dataStorage.saveStudents(new ArrayList<>(students.values()));
    }
}
