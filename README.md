# Student Management System

A college Student Management System built entirely with core Java. The application uses **Java Swing** for the graphical user interface and **Java Serialization** for persistent file-based storage.

## Features

- **Add Student** – Register new students with ID, name, age, department, email, phone, year, and GPA.
- **View Students** – Browse all student records in a sortable table.
- **Search** – Search students by ID, name, department, or email.
- **Update Student** – Fetch and modify existing student records.
- **Delete Student** – Remove student records with confirmation.

## Project Structure

```
src/
└── com/studentmanagement/
    ├── Main.java              # Application entry point
    ├── model/
    │   └── Student.java       # Student data model
    ├── dao/
    │   └── StudentDAO.java    # Data access layer (file-based CRUD)
    └── ui/
        └── MainFrame.java     # Swing GUI with tabbed interface
```

## Prerequisites

- **Java 8** or higher (JDK)

## How to Compile and Run

```bash
# Compile
javac -d out src/com/studentmanagement/model/Student.java \
              src/com/studentmanagement/dao/StudentDAO.java \
              src/com/studentmanagement/ui/MainFrame.java \
              src/com/studentmanagement/Main.java

# Run
java -cp out com.studentmanagement.Main
```

## Data Storage

Student records are stored in a `students.dat` file in the working directory using Java object serialization. The file is created automatically when the first student is added.