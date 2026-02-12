#!/bin/bash
# Demo script for Student Management System

echo "========================================="
echo "Student Management System - Demo Script"
echo "========================================="
echo ""

# Compile the application
echo "Step 1: Compiling the application..."
javac -d bin -sourcepath src/main/java src/main/java/com/studentmanagement/StudentManagementSystem.java

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

echo "✓ Compilation successful!"
echo ""

# Run a sample demonstration
echo "Step 2: Running sample demonstration..."
echo ""
echo "This demo will:"
echo "  - Add a sample student"
echo "  - Record attendance"
echo "  - Add academic records"
echo "  - Generate a summary report"
echo ""

# Create sample data input
cat > /tmp/demo_input.txt << 'EOF'
1
1
S001
Alice Johnson
alice.johnson@university.edu
9876543210
Computer Science
5
2
6
2
1
S001
2024-01-15
Data Structures
1
1
S001
2024-01-16
Data Structures
1
1
S001
2024-01-17
Algorithms
1
1
S001
2024-01-18
Algorithms
2
5
3
1
S001
Data Structures
5
95.0
1
S001
Algorithms
5
88.5
1
S001
Database Systems
5
92.0
1
S001
Operating Systems
5
85.5
5
4
1
S001
4
5
EOF

# Run the application with sample input
java -cp bin com.studentmanagement.StudentManagementSystem < /tmp/demo_input.txt

echo ""
echo "========================================="
echo "Demo completed successfully!"
echo "========================================="
echo ""
echo "Data has been persisted to the 'data/' directory."
echo "You can run the application again to see the saved data."
echo ""
echo "To run manually: ./run.sh"
