package com.studentmanagement.util;

import com.studentmanagement.model.AcademicRecord;
import com.studentmanagement.model.Attendance;
import com.studentmanagement.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles data persistence using file-based storage.
 * Demonstrates OOP principles: Separation of Concerns, Single Responsibility
 */
public class DataStorage {
    private static final String DATA_DIR = "data";
    private static final String STUDENTS_FILE = DATA_DIR + "/students.dat";
    private static final String ATTENDANCE_FILE = DATA_DIR + "/attendance.dat";
    private static final String ACADEMIC_RECORDS_FILE = DATA_DIR + "/academic_records.dat";
    
    public DataStorage() {
        createDataDirectory();
    }
    
    private void createDataDirectory() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.err.println("Error: Failed to create data directory. Please check file permissions.");
            }
        }
    }
    
    /**
     * Save students to file
     */
    public void saveStudents(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(STUDENTS_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }
    
    /**
     * Load students from file
     */
    @SuppressWarnings("unchecked")
    public List<Student> loadStudents() {
        File file = new File(STUDENTS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(STUDENTS_FILE))) {
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading students: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * Save attendance records to file
     */
    public void saveAttendance(List<Attendance> attendance) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ATTENDANCE_FILE))) {
            oos.writeObject(attendance);
        } catch (IOException e) {
            System.err.println("Error saving attendance: " + e.getMessage());
        }
    }
    
    /**
     * Load attendance records from file
     */
    @SuppressWarnings("unchecked")
    public List<Attendance> loadAttendance() {
        File file = new File(ATTENDANCE_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ATTENDANCE_FILE))) {
            return (List<Attendance>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading attendance: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * Save academic records to file
     */
    public void saveAcademicRecords(List<AcademicRecord> records) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ACADEMIC_RECORDS_FILE))) {
            oos.writeObject(records);
        } catch (IOException e) {
            System.err.println("Error saving academic records: " + e.getMessage());
        }
    }
    
    /**
     * Load academic records from file
     */
    @SuppressWarnings("unchecked")
    public List<AcademicRecord> loadAcademicRecords() {
        File file = new File(ACADEMIC_RECORDS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ACADEMIC_RECORDS_FILE))) {
            return (List<AcademicRecord>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading academic records: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
