# Student Management System

A console-based Java application for managing student attendance and academic records, designed using Object-Oriented Programming principles.

## Overview

The Student Management System is a comprehensive solution for educational institutions to manage:
- Student information and records
- Attendance tracking and reporting
- Academic performance and grades
- Statistical reports and analytics

## Features

### 1. Student Management
- **Add New Students**: Register students with personal and academic details
- **View All Students**: Display complete list of registered students
- **Search Students**: Find students by ID or name
- **Update Student Information**: Modify student details
- **Delete Students**: Remove student records from the system

### 2. Attendance Management
- **Record Attendance**: Mark student attendance with status (Present, Absent, Late, Excused)
- **View Student Attendance**: Check individual student attendance history
- **View Attendance by Date**: See all attendance records for a specific date
- **Calculate Attendance Percentage**: Get overall and subject-wise attendance statistics

### 3. Academic Records Management
- **Add Academic Records**: Record student marks for subjects
- **Automatic Grading**: System automatically calculates grades based on marks
- **View Student Records**: Display all academic records for a student
- **View Records by Semester**: Filter records by semester
- **Calculate Overall Performance**: Get comprehensive academic performance metrics

### 4. Reports
- **Student Summary Report**: Complete overview of a student's academic and attendance data
- **Department Statistics**: View department-wise student distribution
- **Attendance Summary**: Overall attendance statistics for all students

## Object-Oriented Design

The application demonstrates key OOP principles:

### Encapsulation
- Data is encapsulated within classes (Student, Attendance, AcademicRecord)
- Private fields with public getter/setter methods
- Business logic is contained within appropriate classes

### Modularity
- Separated into distinct packages:
  - `model`: Domain entities (Student, Attendance, AcademicRecord)
  - `service`: Business logic managers (StudentManager, AttendanceManager, AcademicRecordManager)
  - `ui`: User interface (ConsoleUI)
  - `util`: Utility classes (DataStorage)

### Separation of Concerns
- Model layer: Data representation
- Service layer: Business logic
- UI layer: User interaction
- Utility layer: Data persistence

### Single Responsibility Principle
- Each class has a single, well-defined purpose
- StudentManager handles only student operations
- AttendanceManager handles only attendance operations
- AcademicRecordManager handles only academic records

## Project Structure

```
Student-management-system/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── studentmanagement/
│                   ├── model/
│                   │   ├── Student.java
│                   │   ├── Attendance.java
│                   │   └── AcademicRecord.java
│                   ├── service/
│                   │   ├── StudentManager.java
│                   │   ├── AttendanceManager.java
│                   │   └── AcademicRecordManager.java
│                   ├── ui/
│                   │   └── ConsoleUI.java
│                   ├── util/
│                   │   └── DataStorage.java
│                   └── StudentManagementSystem.java
├── data/                     (created at runtime)
├── .gitignore
├── run.sh
└── README.md
```

## Requirements

- Java Development Kit (JDK) 8 or higher
- Terminal/Command Prompt access

## Installation and Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/samyakkumarsingh/Student-management-system.git
   cd Student-management-system
   ```

2. **Ensure Java is installed**:
   ```bash
   java -version
   ```

## How to Run

### Using the Run Script (Linux/Mac):
```bash
./run.sh
```

### Manual Compilation and Execution:

1. **Compile the application**:
   ```bash
   javac -d bin -sourcepath src/main/java src/main/java/com/studentmanagement/StudentManagementSystem.java
   ```

2. **Run the application**:
   ```bash
   java -cp bin com.studentmanagement.StudentManagementSystem
   ```

### On Windows:
```cmd
javac -d bin -sourcepath src\main\java src\main\java\com\studentmanagement\StudentManagementSystem.java
java -cp bin com.studentmanagement.StudentManagementSystem
```

## Usage Guide

### Main Menu
When you start the application, you'll see the main menu with options:
1. Student Management
2. Attendance Management
3. Academic Records Management
4. Reports
5. Exit

### Adding a Student
1. Select option 1 (Student Management)
2. Select option 1 (Add New Student)
3. Enter student details as prompted:
   - Student ID (unique identifier)
   - Name
   - Email
   - Phone Number
   - Department
   - Semester

### Recording Attendance
1. Select option 2 (Attendance Management)
2. Select option 1 (Record Attendance)
3. Enter student ID
4. Enter date (or press Enter for today)
5. Enter subject name
6. Select attendance status

### Adding Academic Records
1. Select option 3 (Academic Records Management)
2. Select option 1 (Add Academic Record)
3. Enter student ID
4. Enter subject name
5. Enter semester
6. Enter marks (0-100)
   - System automatically calculates grade

### Viewing Reports
1. Select option 4 (Reports)
2. Choose the type of report:
   - Student Summary (complete student profile)
   - Department Statistics
   - Attendance Summary

## Data Persistence

The application uses file-based storage to persist data:
- All data is automatically saved when changes are made
- Data files are stored in the `data/` directory
- Data is loaded automatically when the application starts

**Note**: Do not manually edit the data files as they are serialized objects.

## Grading System

The application uses the following grading scale:
- **A+**: 90-100 marks
- **A**: 80-89 marks
- **B+**: 70-79 marks
- **B**: 60-69 marks
- **C**: 50-59 marks
- **D**: 40-49 marks
- **F**: 0-39 marks (Fail)

Passing marks: 40 and above

## Technical Details

### Technologies Used
- **Core Java**: Application logic
- **Java I/O**: File-based data persistence using serialization
- **Java Collections**: Data structures (HashMap, ArrayList)
- **Java Streams**: Data filtering and processing
- **Java Time API**: Date handling for attendance

### Design Patterns
- **Repository Pattern**: DataStorage class for data access
- **Manager Pattern**: Service classes for business logic
- **MVC-like Structure**: Separation of Model, UI, and Business Logic

## Future Enhancements

Potential improvements for future versions:
- Database integration (MySQL/PostgreSQL)
- User authentication and authorization
- Export reports to PDF/Excel
- Email notifications for low attendance
- GUI interface using JavaFX/Swing
- RESTful API for web/mobile integration
- Multi-user support with different roles (Admin, Teacher, Student)

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is open source and available under the MIT License.

## Author

Samyak Kumar Singh

## Contact

For questions or feedback, please open an issue on GitHub.

---

**Note**: This is a demonstration project showcasing Object-Oriented Programming principles in Core Java.