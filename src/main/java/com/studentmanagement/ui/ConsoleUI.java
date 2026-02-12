package com.studentmanagement.ui;

import com.studentmanagement.model.AcademicRecord;
import com.studentmanagement.model.Attendance;
import com.studentmanagement.model.Attendance.AttendanceStatus;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.AcademicRecordManager;
import com.studentmanagement.service.AttendanceManager;
import com.studentmanagement.service.StudentManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Console-based User Interface for the Student Management System.
 * Demonstrates OOP principles: Separation of Concerns, User Interaction Layer
 */
public class ConsoleUI {
    private StudentManager studentManager;
    private AttendanceManager attendanceManager;
    private AcademicRecordManager academicRecordManager;
    private Scanner scanner;
    private DateTimeFormatter dateFormatter;
    
    public ConsoleUI() {
        this.studentManager = new StudentManager();
        this.attendanceManager = new AttendanceManager();
        this.academicRecordManager = new AcademicRecordManager();
        this.scanner = new Scanner(System.in);
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
    
    /**
     * Display main menu and handle user input
     */
    public void displayMainMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║   STUDENT MANAGEMENT SYSTEM - Main Menu    ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.println("1. Student Management");
            System.out.println("2. Attendance Management");
            System.out.println("3. Academic Records Management");
            System.out.println("4. Reports");
            System.out.println("5. Exit");
            System.out.print("\nEnter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    displayStudentMenu();
                    break;
                case 2:
                    displayAttendanceMenu();
                    break;
                case 3:
                    displayAcademicRecordsMenu();
                    break;
                case 4:
                    displayReportsMenu();
                    break;
                case 5:
                    System.out.println("\nThank you for using Student Management System!");
                    return;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
        }
    }
    
    /**
     * Student Management Menu
     */
    private void displayStudentMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║        Student Management Menu             ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Back to Main Menu");
            System.out.print("\nEnter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
        }
    }
    
    /**
     * Attendance Management Menu
     */
    private void displayAttendanceMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║       Attendance Management Menu           ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.println("1. Record Attendance");
            System.out.println("2. View Student Attendance");
            System.out.println("3. View Attendance by Date");
            System.out.println("4. Calculate Attendance Percentage");
            System.out.println("5. Back to Main Menu");
            System.out.print("\nEnter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    recordAttendance();
                    break;
                case 2:
                    viewStudentAttendance();
                    break;
                case 3:
                    viewAttendanceByDate();
                    break;
                case 4:
                    calculateAttendancePercentage();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
        }
    }
    
    /**
     * Academic Records Management Menu
     */
    private void displayAcademicRecordsMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║      Academic Records Management Menu      ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.println("1. Add Academic Record");
            System.out.println("2. View Student Records");
            System.out.println("3. View Records by Semester");
            System.out.println("4. Calculate Overall Performance");
            System.out.println("5. Back to Main Menu");
            System.out.print("\nEnter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    addAcademicRecord();
                    break;
                case 2:
                    viewStudentRecords();
                    break;
                case 3:
                    viewRecordsBySemester();
                    break;
                case 4:
                    calculateOverallPerformance();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
        }
    }
    
    /**
     * Reports Menu
     */
    private void displayReportsMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║              Reports Menu                  ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.println("1. Student Summary Report");
            System.out.println("2. Department-wise Statistics");
            System.out.println("3. Attendance Summary");
            System.out.println("4. Back to Main Menu");
            System.out.print("\nEnter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    studentSummaryReport();
                    break;
                case 2:
                    departmentStatistics();
                    break;
                case 3:
                    attendanceSummary();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
        }
    }
    
    // Student Management Methods
    
    private void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();
        
        if (id.isEmpty()) {
            System.out.println("Error: Student ID cannot be empty!");
            return;
        }
        
        if (studentManager.studentExists(id)) {
            System.out.println("Error: Student with ID " + id + " already exists!");
            return;
        }
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("Error: Name cannot be empty!");
            return;
        }
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        System.out.print("Enter Semester: ");
        int semester = getIntInput();
        
        Student student = new Student(id, name, email, phone, department, semester);
        if (studentManager.addStudent(student)) {
            System.out.println("\n✓ Student added successfully!");
        } else {
            System.out.println("\n✗ Failed to add student!");
        }
    }
    
    private void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = studentManager.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        
        System.out.println("\nTotal Students: " + students.size());
        System.out.println("─".repeat(100));
        System.out.printf("%-15s %-25s %-30s %-15s %-20s %-10s%n",
                "ID", "Name", "Email", "Phone", "Department", "Semester");
        System.out.println("─".repeat(100));
        
        for (Student student : students) {
            System.out.printf("%-15s %-25s %-30s %-15s %-20s %-10d%n",
                    student.getStudentId(),
                    student.getName(),
                    student.getEmail(),
                    student.getPhoneNumber(),
                    student.getDepartment(),
                    student.getSemester());
        }
    }
    
    private void searchStudent() {
        System.out.println("\n--- Search Student ---");
        System.out.print("Enter Student ID or Name: ");
        String query = scanner.nextLine();
        
        // Try to find by ID first
        Student student = studentManager.getStudent(query);
        if (student != null) {
            displayStudentDetails(student);
            return;
        }
        
        // Search by name
        List<Student> students = studentManager.searchByName(query);
        if (students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            System.out.println("\nFound " + students.size() + " student(s):");
            for (Student s : students) {
                displayStudentDetails(s);
            }
        }
    }
    
    private void updateStudent() {
        System.out.println("\n--- Update Student ---");
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        
        Student student = studentManager.getStudent(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("\nCurrent Details:");
        displayStudentDetails(student);
        
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        System.out.print("Name [" + student.getName() + "]: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) student.setName(name);
        
        System.out.print("Email [" + student.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) student.setEmail(email);
        
        System.out.print("Phone [" + student.getPhoneNumber() + "]: ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) student.setPhoneNumber(phone);
        
        System.out.print("Department [" + student.getDepartment() + "]: ");
        String department = scanner.nextLine();
        if (!department.isEmpty()) student.setDepartment(department);
        
        System.out.print("Semester [" + student.getSemester() + "]: ");
        String semesterStr = scanner.nextLine();
        if (!semesterStr.isEmpty()) {
            try {
                student.setSemester(Integer.parseInt(semesterStr));
            } catch (NumberFormatException e) {
                System.out.println("Invalid semester value, keeping current.");
            }
        }
        
        if (studentManager.updateStudent(student)) {
            System.out.println("\n✓ Student updated successfully!");
        } else {
            System.out.println("\n✗ Failed to update student!");
        }
    }
    
    private void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        
        Student student = studentManager.getStudent(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        displayStudentDetails(student);
        System.out.print("\nAre you sure you want to delete this student? (yes/no): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("yes")) {
            if (studentManager.deleteStudent(id)) {
                System.out.println("\n✓ Student deleted successfully!");
            } else {
                System.out.println("\n✗ Failed to delete student!");
            }
        } else {
            System.out.println("\nDeletion cancelled.");
        }
    }
    
    private void displayStudentDetails(Student student) {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("Student ID:    " + student.getStudentId());
        System.out.println("Name:          " + student.getName());
        System.out.println("Email:         " + student.getEmail());
        System.out.println("Phone:         " + student.getPhoneNumber());
        System.out.println("Department:    " + student.getDepartment());
        System.out.println("Semester:      " + student.getSemester());
        System.out.println("─".repeat(50));
    }
    
    // Attendance Management Methods
    
    private void recordAttendance() {
        System.out.println("\n--- Record Attendance ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        if (!studentManager.studentExists(studentId)) {
            System.out.println("Error: Student not found!");
            return;
        }
        
        System.out.print("Enter Date (yyyy-MM-dd) [or press Enter for today]: ");
        String dateStr = scanner.nextLine();
        LocalDate date;
        
        if (dateStr.isEmpty()) {
            date = LocalDate.now();
        } else {
            try {
                date = LocalDate.parse(dateStr, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Expected format: yyyy-MM-dd");
                return;
            }
        }
        
        System.out.print("Enter Subject: ");
        String subject = scanner.nextLine();
        
        System.out.println("\nAttendance Status:");
        System.out.println("1. PRESENT");
        System.out.println("2. ABSENT");
        System.out.println("3. LATE");
        System.out.println("4. EXCUSED");
        System.out.print("Select status: ");
        
        int statusChoice = getIntInput();
        AttendanceStatus status;
        
        switch (statusChoice) {
            case 1: status = AttendanceStatus.PRESENT; break;
            case 2: status = AttendanceStatus.ABSENT; break;
            case 3: status = AttendanceStatus.LATE; break;
            case 4: status = AttendanceStatus.EXCUSED; break;
            default:
                System.out.println("Invalid status!");
                return;
        }
        
        Attendance attendance = new Attendance(studentId, date, status, subject);
        if (attendanceManager.recordAttendance(attendance)) {
            System.out.println("\n✓ Attendance recorded successfully!");
        } else {
            System.out.println("\n✗ Failed to record attendance!");
        }
    }
    
    private void viewStudentAttendance() {
        System.out.println("\n--- View Student Attendance ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        if (!studentManager.studentExists(studentId)) {
            System.out.println("Error: Student not found!");
            return;
        }
        
        List<Attendance> records = attendanceManager.getStudentAttendance(studentId);
        
        if (records.isEmpty()) {
            System.out.println("No attendance records found!");
            return;
        }
        
        Student student = studentManager.getStudent(studentId);
        System.out.println("\nAttendance Records for: " + student.getName());
        System.out.println("─".repeat(80));
        System.out.printf("%-15s %-30s %-15s%n", "Date", "Subject", "Status");
        System.out.println("─".repeat(80));
        
        for (Attendance record : records) {
            System.out.printf("%-15s %-30s %-15s%n",
                    record.getDate(),
                    record.getSubject(),
                    record.getStatus());
        }
        
        double percentage = attendanceManager.calculateAttendancePercentage(studentId);
        System.out.println("─".repeat(80));
        System.out.printf("Overall Attendance: %.2f%%%n", percentage);
    }
    
    private void viewAttendanceByDate() {
        System.out.println("\n--- View Attendance by Date ---");
        System.out.print("Enter Date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        
        try {
            LocalDate date = LocalDate.parse(dateStr, dateFormatter);
            List<Attendance> records = attendanceManager.getAttendanceByDate(date);
            
            if (records.isEmpty()) {
                System.out.println("No attendance records found for " + date);
                return;
            }
            
            System.out.println("\nAttendance Records for: " + date);
            System.out.println("─".repeat(80));
            System.out.printf("%-15s %-25s %-30s %-15s%n",
                    "Student ID", "Student Name", "Subject", "Status");
            System.out.println("─".repeat(80));
            
            for (Attendance record : records) {
                Student student = studentManager.getStudent(record.getStudentId());
                String studentName = (student != null) ? student.getName() : "Unknown";
                System.out.printf("%-15s %-25s %-30s %-15s%n",
                        record.getStudentId(),
                        studentName,
                        record.getSubject(),
                        record.getStatus());
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Expected format: yyyy-MM-dd");
        }
    }
    
    private void calculateAttendancePercentage() {
        System.out.println("\n--- Calculate Attendance Percentage ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        if (!studentManager.studentExists(studentId)) {
            System.out.println("Error: Student not found!");
            return;
        }
        
        Student student = studentManager.getStudent(studentId);
        double overallPercentage = attendanceManager.calculateAttendancePercentage(studentId);
        
        System.out.println("\nAttendance Report for: " + student.getName());
        System.out.println("─".repeat(60));
        System.out.printf("Overall Attendance: %.2f%%%n", overallPercentage);
        
        // Show subject-wise attendance
        List<Attendance> records = attendanceManager.getStudentAttendance(studentId);
        if (!records.isEmpty()) {
            System.out.println("\nSubject-wise Attendance:");
            records.stream()
                    .map(Attendance::getSubject)
                    .distinct()
                    .forEach(subject -> {
                        double percentage = attendanceManager.calculateSubjectAttendancePercentage(
                                studentId, subject);
                        System.out.printf("  %-30s: %.2f%%%n", subject, percentage);
                    });
        }
    }
    
    // Academic Records Management Methods
    
    private void addAcademicRecord() {
        System.out.println("\n--- Add Academic Record ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        if (!studentManager.studentExists(studentId)) {
            System.out.println("Error: Student not found!");
            return;
        }
        
        System.out.print("Enter Subject: ");
        String subject = scanner.nextLine();
        System.out.print("Enter Semester: ");
        int semester = getIntInput();
        System.out.print("Enter Marks (0-100): ");
        double marks = getDoubleInput();
        
        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks! Must be between 0 and 100.");
            return;
        }
        
        AcademicRecord record = new AcademicRecord(studentId, subject, semester, marks);
        if (academicRecordManager.addRecord(record)) {
            System.out.println("\n✓ Academic record added successfully!");
            System.out.println("Grade: " + record.getGrade() + " (" + 
                             (record.isPassed() ? "PASS" : "FAIL") + ")");
        } else {
            System.out.println("\n✗ Failed to add academic record!");
        }
    }
    
    private void viewStudentRecords() {
        System.out.println("\n--- View Student Academic Records ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        if (!studentManager.studentExists(studentId)) {
            System.out.println("Error: Student not found!");
            return;
        }
        
        List<AcademicRecord> records = academicRecordManager.getStudentRecords(studentId);
        
        if (records.isEmpty()) {
            System.out.println("No academic records found!");
            return;
        }
        
        Student student = studentManager.getStudent(studentId);
        System.out.println("\nAcademic Records for: " + student.getName());
        System.out.println("─".repeat(80));
        System.out.printf("%-30s %-10s %-10s %-10s %-10s%n",
                "Subject", "Semester", "Marks", "Grade", "Status");
        System.out.println("─".repeat(80));
        
        for (AcademicRecord record : records) {
            System.out.printf("%-30s %-10d %-10.2f %-10s %-10s%n",
                    record.getSubject(),
                    record.getSemester(),
                    record.getMarks(),
                    record.getGrade(),
                    record.isPassed() ? "PASS" : "FAIL");
        }
    }
    
    private void viewRecordsBySemester() {
        System.out.println("\n--- View Records by Semester ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        if (!studentManager.studentExists(studentId)) {
            System.out.println("Error: Student not found!");
            return;
        }
        
        System.out.print("Enter Semester: ");
        int semester = getIntInput();
        
        List<AcademicRecord> records = academicRecordManager.getRecordsBySemester(studentId, semester);
        
        if (records.isEmpty()) {
            System.out.println("No records found for semester " + semester);
            return;
        }
        
        Student student = studentManager.getStudent(studentId);
        System.out.println("\nSemester " + semester + " Records for: " + student.getName());
        System.out.println("─".repeat(70));
        System.out.printf("%-30s %-10s %-10s %-10s%n", "Subject", "Marks", "Grade", "Status");
        System.out.println("─".repeat(70));
        
        for (AcademicRecord record : records) {
            System.out.printf("%-30s %-10.2f %-10s %-10s%n",
                    record.getSubject(),
                    record.getMarks(),
                    record.getGrade(),
                    record.isPassed() ? "PASS" : "FAIL");
        }
        
        double percentage = academicRecordManager.calculateSemesterPercentage(studentId, semester);
        System.out.println("─".repeat(70));
        System.out.printf("Semester Average: %.2f%%%n", percentage);
    }
    
    private void calculateOverallPerformance() {
        System.out.println("\n--- Calculate Overall Performance ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        if (!studentManager.studentExists(studentId)) {
            System.out.println("Error: Student not found!");
            return;
        }
        
        Student student = studentManager.getStudent(studentId);
        List<AcademicRecord> records = academicRecordManager.getStudentRecords(studentId);
        
        if (records.isEmpty()) {
            System.out.println("No academic records found!");
            return;
        }
        
        double percentage = academicRecordManager.calculateOverallPercentage(studentId);
        String grade = academicRecordManager.calculateOverallGrade(studentId);
        boolean passedAll = academicRecordManager.hasPassedAllSubjects(studentId);
        
        System.out.println("\nOverall Performance Report");
        System.out.println("Student: " + student.getName());
        System.out.println("─".repeat(60));
        System.out.printf("Overall Percentage: %.2f%%%n", percentage);
        System.out.println("Overall Grade: " + grade);
        System.out.println("Status: " + (passedAll ? "PASSED ALL SUBJECTS" : "FAILED IN SOME SUBJECTS"));
        System.out.println("Total Subjects: " + records.size());
    }
    
    // Reports Methods
    
    private void studentSummaryReport() {
        System.out.println("\n--- Student Summary Report ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        if (!studentManager.studentExists(studentId)) {
            System.out.println("Error: Student not found!");
            return;
        }
        
        Student student = studentManager.getStudent(studentId);
        
        System.out.println("\n" + "═".repeat(80));
        System.out.println("STUDENT SUMMARY REPORT");
        System.out.println("═".repeat(80));
        
        // Student Details
        System.out.println("\nSTUDENT DETAILS:");
        System.out.println("─".repeat(80));
        displayStudentDetails(student);
        
        // Academic Performance
        List<AcademicRecord> academicRecords = academicRecordManager.getStudentRecords(studentId);
        if (!academicRecords.isEmpty()) {
            System.out.println("\nACADEMIC PERFORMANCE:");
            System.out.println("─".repeat(80));
            double percentage = academicRecordManager.calculateOverallPercentage(studentId);
            String grade = academicRecordManager.calculateOverallGrade(studentId);
            System.out.printf("Overall Percentage: %.2f%%%n", percentage);
            System.out.println("Overall Grade: " + grade);
            System.out.println("Total Subjects: " + academicRecords.size());
        }
        
        // Attendance Summary
        List<Attendance> attendanceRecords = attendanceManager.getStudentAttendance(studentId);
        if (!attendanceRecords.isEmpty()) {
            System.out.println("\nATTENDANCE SUMMARY:");
            System.out.println("─".repeat(80));
            double attendancePercentage = attendanceManager.calculateAttendancePercentage(studentId);
            System.out.printf("Overall Attendance: %.2f%%%n", attendancePercentage);
            System.out.println("Total Records: " + attendanceRecords.size());
        }
        
        System.out.println("═".repeat(80));
    }
    
    private void departmentStatistics() {
        System.out.println("\n--- Department-wise Statistics ---");
        System.out.print("Enter Department Name: ");
        String department = scanner.nextLine();
        
        List<Student> students = studentManager.getStudentsByDepartment(department);
        
        if (students.isEmpty()) {
            System.out.println("No students found in " + department + " department!");
            return;
        }
        
        System.out.println("\n" + department + " Department Statistics");
        System.out.println("─".repeat(60));
        System.out.println("Total Students: " + students.size());
        
        // Semester-wise distribution
        Map<Integer, Long> semesterDist = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getSemester,
                        Collectors.counting()));
        
        System.out.println("\nSemester-wise Distribution:");
        semesterDist.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> 
                    System.out.println("  Semester " + entry.getKey() + ": " + entry.getValue() + " students"));
    }
    
    private void attendanceSummary() {
        System.out.println("\n--- Attendance Summary ---");
        
        List<Student> students = studentManager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        
        System.out.println("\n" + "═".repeat(80));
        System.out.println("ATTENDANCE SUMMARY - ALL STUDENTS");
        System.out.println("═".repeat(80));
        System.out.printf("%-15s %-25s %-20s%n", "Student ID", "Name", "Attendance %");
        System.out.println("─".repeat(80));
        
        for (Student student : students) {
            double percentage = attendanceManager.calculateAttendancePercentage(student.getStudentId());
            if (!attendanceManager.getStudentAttendance(student.getStudentId()).isEmpty()) {
                System.out.printf("%-15s %-25s %-20.2f%%%n",
                        student.getStudentId(),
                        student.getName(),
                        percentage);
            }
        }
        System.out.println("═".repeat(80));
    }
    
    // Utility Methods
    
    private int getIntInput() {
        while (true) {
            try {
                if (!scanner.hasNextLine()) {
                    System.out.println("\nEnd of input reached.");
                    System.exit(0);
                }
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }
    
    private double getDoubleInput() {
        while (true) {
            try {
                if (!scanner.hasNextLine()) {
                    System.out.println("\nEnd of input reached.");
                    System.exit(0);
                }
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }
}
