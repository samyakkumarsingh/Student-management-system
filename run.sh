#!/bin/bash
# Script to compile and run the Student Management System

echo "Compiling Student Management System..."
javac -d bin -sourcepath src/main/java src/main/java/com/studentmanagement/StudentManagementSystem.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Starting Student Management System..."
    echo ""
    java -cp bin com.studentmanagement.StudentManagementSystem
else
    echo "Compilation failed!"
    exit 1
fi
