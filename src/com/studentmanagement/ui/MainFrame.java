package com.studentmanagement.ui;

import com.studentmanagement.dao.StudentDAO;
import com.studentmanagement.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private final StudentDAO studentDAO;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private final String[] columnNames = {"ID", "Name", "Age", "Department", "Email", "Phone", "Year", "GPA"};

    // Add Student form fields
    private JTextField addIdField, addNameField, addAgeField, addDeptField;
    private JTextField addEmailField, addPhoneField, addYearField, addGpaField;

    // Update Student form fields
    private JTextField updateIdField, updateNameField, updateAgeField, updateDeptField;
    private JTextField updateEmailField, updatePhoneField, updateYearField, updateGpaField;

    // Search field
    private JTextField searchField;

    public MainFrame() {
        studentDAO = new StudentDAO();
        initUI();
    }

    private void initUI() {
        setTitle("College Student Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("View Students", createViewPanel());
        tabbedPane.addTab("Add Student", createAddPanel());
        tabbedPane.addTab("Update Student", createUpdatePanel());
        tabbedPane.addTab("Delete Student", createDeletePanel());

        add(tabbedPane);
        refreshTable();
    }

    // ===== View / Search Panel =====
    private JPanel createViewPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Search bar
        JPanel searchPanel = new JPanel(new BorderLayout(5, 0));
        searchField = new JTextField();
        JButton searchBtn = new JButton("Search");
        JButton refreshBtn = new JButton("Refresh");
        JPanel searchBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        searchBtnPanel.add(searchBtn);
        searchBtnPanel.add(refreshBtn);
        searchPanel.add(new JLabel("Search: "), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchBtnPanel, BorderLayout.EAST);

        // Table
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(studentTable);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Listeners
        searchBtn.addActionListener(e -> performSearch());
        refreshBtn.addActionListener(e -> refreshTable());

        return panel;
    }

    // ===== Add Student Panel =====
    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));

        addIdField = new JTextField();
        addNameField = new JTextField();
        addAgeField = new JTextField();
        addDeptField = new JTextField();
        addEmailField = new JTextField();
        addPhoneField = new JTextField();
        addYearField = new JTextField();
        addGpaField = new JTextField();

        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(addIdField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(addNameField);
        formPanel.add(new JLabel("Age:"));
        formPanel.add(addAgeField);
        formPanel.add(new JLabel("Department:"));
        formPanel.add(addDeptField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(addEmailField);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(addPhoneField);
        formPanel.add(new JLabel("Year:"));
        formPanel.add(addYearField);
        formPanel.add(new JLabel("GPA:"));
        formPanel.add(addGpaField);

        JButton addBtn = new JButton("Add Student");
        JButton clearBtn = new JButton("Clear");
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.add(addBtn);
        btnPanel.add(clearBtn);
        formPanel.add(new JLabel());
        formPanel.add(btnPanel);

        panel.add(new JLabel("Add New Student", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        addBtn.addActionListener(e -> addStudent());
        clearBtn.addActionListener(e -> clearAddFields());

        return panel;
    }

    // ===== Update Student Panel =====
    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));

        updateIdField = new JTextField();
        updateNameField = new JTextField();
        updateAgeField = new JTextField();
        updateDeptField = new JTextField();
        updateEmailField = new JTextField();
        updatePhoneField = new JTextField();
        updateYearField = new JTextField();
        updateGpaField = new JTextField();

        JButton fetchBtn = new JButton("Fetch");

        JPanel idPanel = new JPanel(new BorderLayout(5, 0));
        idPanel.add(updateIdField, BorderLayout.CENTER);
        idPanel.add(fetchBtn, BorderLayout.EAST);

        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(idPanel);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(updateNameField);
        formPanel.add(new JLabel("Age:"));
        formPanel.add(updateAgeField);
        formPanel.add(new JLabel("Department:"));
        formPanel.add(updateDeptField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(updateEmailField);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(updatePhoneField);
        formPanel.add(new JLabel("Year:"));
        formPanel.add(updateYearField);
        formPanel.add(new JLabel("GPA:"));
        formPanel.add(updateGpaField);

        JButton updateBtn = new JButton("Update Student");
        JButton clearBtn = new JButton("Clear");
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.add(updateBtn);
        btnPanel.add(clearBtn);
        formPanel.add(new JLabel());
        formPanel.add(btnPanel);

        // Instructions
        formPanel.add(new JLabel());
        formPanel.add(new JLabel("Enter ID and click Fetch to load student data."));

        panel.add(new JLabel("Update Student", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        fetchBtn.addActionListener(e -> fetchStudentForUpdate());
        updateBtn.addActionListener(e -> updateStudent());
        clearBtn.addActionListener(e -> clearUpdateFields());

        return panel;
    }

    // ===== Delete Student Panel =====
    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField deleteIdField = new JTextField();
        JButton deleteBtn = new JButton("Delete Student");

        centerPanel.add(new JLabel("Student ID:"));
        centerPanel.add(deleteIdField);
        centerPanel.add(new JLabel());
        centerPanel.add(deleteBtn);
        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel("Enter the Student ID to delete."));

        panel.add(new JLabel("Delete Student", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.NORTH);

        deleteBtn.addActionListener(e -> {
            String id = deleteIdField.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Student ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Student student = studentDAO.getStudentById(id);
            if (student == null) {
                JOptionPane.showMessageDialog(this, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete student:\n" + student.getName() + " (" + student.getId() + ")?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (studentDAO.deleteStudent(id)) {
                    JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                    deleteIdField.setText("");
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete student.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

    // ===== Action Methods =====

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Student> students = studentDAO.getAllStudents();
        for (Student s : students) {
            tableModel.addRow(new Object[]{
                    s.getId(), s.getName(), s.getAge(), s.getDepartment(),
                    s.getEmail(), s.getPhone(), s.getYear(), s.getGpa()
            });
        }
    }

    private void performSearch() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            refreshTable();
            return;
        }
        tableModel.setRowCount(0);
        List<Student> students = studentDAO.searchStudents(keyword);
        for (Student s : students) {
            tableModel.addRow(new Object[]{
                    s.getId(), s.getName(), s.getAge(), s.getDepartment(),
                    s.getEmail(), s.getPhone(), s.getYear(), s.getGpa()
            });
        }
    }

    private String validateFields(String id, String name, int age, String dept,
                                   String email, String phone, int year, double gpa) {
        if (id.isEmpty() || name.isEmpty() || dept.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            return "All fields are required.";
        }
        if (age < 16 || age > 100) {
            return "Age must be between 16 and 100.";
        }
        if (year < 1 || year > 6) {
            return "Year must be between 1 and 6.";
        }
        if (gpa < 0.0 || gpa > 10.0) {
            return "GPA must be between 0.0 and 10.0.";
        }
        if (!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            return "Please enter a valid email address.";
        }
        if (!phone.matches("^\\d{7,15}$")) {
            return "Phone must contain 7 to 15 digits.";
        }
        return null;
    }

    private void addStudent() {
        try {
            String id = addIdField.getText().trim();
            String name = addNameField.getText().trim();
            int age = Integer.parseInt(addAgeField.getText().trim());
            String dept = addDeptField.getText().trim();
            String email = addEmailField.getText().trim();
            String phone = addPhoneField.getText().trim();
            int year = Integer.parseInt(addYearField.getText().trim());
            double gpa = Double.parseDouble(addGpaField.getText().trim());

            String error = validateFields(id, name, age, dept, email, phone, year, gpa);
            if (error != null) {
                JOptionPane.showMessageDialog(this, error, "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student student = new Student(id, name, age, dept, email, phone, year, gpa);
            if (studentDAO.addStudent(student)) {
                JOptionPane.showMessageDialog(this, "Student added successfully!");
                clearAddFields();
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Student with this ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Age, Year, and GPA.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearAddFields() {
        addIdField.setText("");
        addNameField.setText("");
        addAgeField.setText("");
        addDeptField.setText("");
        addEmailField.setText("");
        addPhoneField.setText("");
        addYearField.setText("");
        addGpaField.setText("");
    }

    private void fetchStudentForUpdate() {
        String id = updateIdField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Student ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Student student = studentDAO.getStudentById(id);
        if (student == null) {
            JOptionPane.showMessageDialog(this, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        updateNameField.setText(student.getName());
        updateAgeField.setText(String.valueOf(student.getAge()));
        updateDeptField.setText(student.getDepartment());
        updateEmailField.setText(student.getEmail());
        updatePhoneField.setText(student.getPhone());
        updateYearField.setText(String.valueOf(student.getYear()));
        updateGpaField.setText(String.valueOf(student.getGpa()));
    }

    private void updateStudent() {
        try {
            String id = updateIdField.getText().trim();
            String name = updateNameField.getText().trim();
            int age = Integer.parseInt(updateAgeField.getText().trim());
            String dept = updateDeptField.getText().trim();
            String email = updateEmailField.getText().trim();
            String phone = updatePhoneField.getText().trim();
            int year = Integer.parseInt(updateYearField.getText().trim());
            double gpa = Double.parseDouble(updateGpaField.getText().trim());

            String error = validateFields(id, name, age, dept, email, phone, year, gpa);
            if (error != null) {
                JOptionPane.showMessageDialog(this, error, "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student student = new Student(id, name, age, dept, email, phone, year, gpa);
            if (studentDAO.updateStudent(student)) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
                clearUpdateFields();
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Age, Year, and GPA.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearUpdateFields() {
        updateIdField.setText("");
        updateNameField.setText("");
        updateAgeField.setText("");
        updateDeptField.setText("");
        updateEmailField.setText("");
        updatePhoneField.setText("");
        updateYearField.setText("");
        updateGpaField.setText("");
    }
}
