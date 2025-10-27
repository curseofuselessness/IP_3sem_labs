package com.example;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    long num;
    String name;
    int group;
    double grade;
    
    public Student(long num, String name, int group, double grade) {
        this.num = num;
        this.name = name;
        this.group = group;
        this.grade = grade;
    }
    
    boolean equals(Student other) {
        return this.num == other.num &&
               this.name.equals(other.name) &&
               this.group == other.group &&
               this.grade == other.grade;
    }
    
    @Override
    public String toString() {
        return num + " " + name + " " + group + " " + grade;
    }
}

public class StudentsOperations {
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("ОПЕРАЦИИ НАД ФАЙЛАМИ СТУДЕНТОВ");
        
        while (true) {
            System.out.println("\nМЕНЮ:");
            System.out.println("1. Объединение");
            System.out.println("2. Пересечение");
            System.out.println("3. Разность");
            System.out.println("0. Выход");
            System.out.print("Выберите операцию: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 0) break;
            
            try {
                System.out.print("Введите имя первого файла: ");
                String file1 = scanner.nextLine();
                
                System.out.print("Введите имя второго файла: ");
                String file2 = scanner.nextLine();
                
                System.out.print("Введите имя выходного файла: ");
                String outputFile = scanner.nextLine();
                
                List<Student> setA = readStudents(file1);
                List<Student> setB = readStudents(file2);
                
                List<Student> result;
                
                if (choice == 1) {
                    result = union(setA, setB);
                    System.out.println("Объединение завершено. Результат: " + result.size() + " записей");
                } else if (choice == 2) {
                    result = intersection(setA, setB);
                    System.out.println("Пересечение завершено. Результат: " + result.size() + " записей");
                } else if (choice == 3) {
                    result = difference(setA, setB);
                    System.out.println("Разность завершено. Результат: " + result.size() + " записей");
                } else {
                    System.out.println("Неверный выбор!");
                    continue;
                }
                
                writeStudents(outputFile, result);
                
            } catch (IOException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    static List<Student> readStudents(String filename) throws IOException {
        List<Student> students = new ArrayList<>();

        try (FileReader reader = new FileReader(filename); 
        Scanner fileScanner = new Scanner(reader)) {
            
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(" ");

                    if (parts.length >= 4) {
                        long num = Long.parseLong(parts[0].trim());
                        String name = parts[1].trim();
                        int group = Integer.parseInt(parts[2].trim());
                        double grade = Double.parseDouble(parts[3].trim());

                        students.add(new Student(num, name, group, grade));
                    }
                }
            }
            
        }
        return students;
    }
    
    static void writeStudents(String filename, List<Student> students) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Student student : students) {
                writer.write(student.toString() + "\n");
            }
        }
    }
    
    static List<Student> union(List<Student> set1, List<Student> set2) {
        List<Student> result = new ArrayList<>();
        
        for (Student student : set1) {
            if (!containsStudent(result, student)) {
                result.add(student);
            }
        }
        
        for (Student student : set2) {
            if (!containsStudent(result, student)) {
                result.add(student);
            }
        }
        
        return result;
    }
    
    static List<Student> intersection(List<Student> set1, List<Student> set2) {
        List<Student> result = new ArrayList<>();
        
        for (Student student1 : set1) {
            for (Student student2 : set2) {
                if (student1.equals(student2)) {
                    if (!containsStudent(result, student1)) {
                        result.add(student1);
                    }
                    break;
                }
            }
        }
        
        return result;
    }
    
    static List<Student> difference(List<Student> set1, List<Student> set2) {
        List<Student> result = new ArrayList<>();
        
        for (Student student1 : set1) {
            boolean found = false;
            for (Student student2 : set2) {
                if (student1.equals(student2)) {
                    found = true;
                    break;
                }
            }
            if (!found && !containsStudent(result, student1)) {
                result.add(student1);
            }
        }
        
        return result;
    }
    
    static boolean containsStudent(List<Student> list, Student student) {
        for (Student s : list) {
            if (s.equals(student)) {
                return true;
            }
        }
        return false;
    }
}