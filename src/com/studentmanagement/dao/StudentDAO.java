package com.studentmanagement.dao;

import com.studentmanagement.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String DATA_FILE = "students.dat";

    public List<Student> getAllStudents() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            @SuppressWarnings("unchecked")
            List<Student> students = (List<Student>) ois.readObject();
            return students;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void saveAllStudents(List<Student> students) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(students);
        }
    }

    public boolean addStudent(Student student) {
        List<Student> students = getAllStudents();
        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                return false;
            }
        }
        students.add(student);
        try {
            saveAllStudents(students);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean updateStudent(Student student) {
        List<Student> students = getAllStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(student.getId())) {
                students.set(i, student);
                try {
                    saveAllStudents(students);
                    return true;
                } catch (IOException e) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean deleteStudent(String id) {
        List<Student> students = getAllStudents();
        boolean removed = students.removeIf(s -> s.getId().equals(id));
        if (removed) {
            try {
                saveAllStudents(students);
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }

    public Student getStudentById(String id) {
        List<Student> students = getAllStudents();
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public List<Student> searchStudents(String keyword) {
        List<Student> students = getAllStudents();
        List<Student> results = new ArrayList<>();
        String lower = keyword.toLowerCase();
        for (Student s : students) {
            if (s.getId().toLowerCase().contains(lower)
                    || s.getName().toLowerCase().contains(lower)
                    || s.getDepartment().toLowerCase().contains(lower)
                    || s.getEmail().toLowerCase().contains(lower)) {
                results.add(s);
            }
        }
        return results;
    }
}
