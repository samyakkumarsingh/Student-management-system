package com.studentmanagement;

import com.studentmanagement.ui.ConsoleUI;

/**
 * Main entry point for the Student Management System.
 * A console-based application for managing student attendance and academic records.
 * 
 * Features:
 * - Student Management (CRUD operations)
 * - Attendance Tracking
 * - Academic Records Management
 * - Comprehensive Reporting
 * 
 * Demonstrates OOP Principles:
 * - Encapsulation
 * - Modularity
 * - Separation of Concerns
 * - Single Responsibility
 * 
 * @author Student Management System Team
 * @version 1.0
 */
public class StudentManagementSystem {
    
    public static void main(String[] args) {
        displayWelcomeBanner();
        
        ConsoleUI ui = new ConsoleUI();
        ui.displayMainMenu();
        
        System.out.println("\nGoodbye!");
    }
    
    private static void displayWelcomeBanner() {
        System.out.println("\n" + "═".repeat(80));
        System.out.println("║" + " ".repeat(78) + "║");
        System.out.println("║" + centerText("STUDENT MANAGEMENT SYSTEM", 78) + "║");
        System.out.println("║" + centerText("Version 1.0", 78) + "║");
        System.out.println("║" + " ".repeat(78) + "║");
        System.out.println("║" + centerText("A Console-Based Application for Managing", 78) + "║");
        System.out.println("║" + centerText("Student Attendance and Academic Records", 78) + "║");
        System.out.println("║" + " ".repeat(78) + "║");
        System.out.println("═".repeat(80));
        System.out.println();
    }
    
    private static String centerText(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - padding - text.length());
    }
}
