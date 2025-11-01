package com.example;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("🎓 ДЕМОНСТРАЦИЯ СИСТЕМЫ ОБРАЗОВАНИЯ С ПАТТЕРНОМ STRATEGY\n");

        List<Student> students = new ArrayList<>();
        
        // Школьники
        students.add(new Student("Анна Петрова", 15, new SchoolEducationStrategy(9)));
        students.add(new Student("Иван Сидоров", 14, new SchoolEducationStrategy(10)));
        students.add(new Student("Мария Козлова", 16, new SchoolEducationStrategy(11)));
        
        // Студенты
        students.add(new Student("Арсений Лазарев", 19, new UniversityEducationStrategy("Прикладная математика", 2)));
        students.add(new Student("Никита Берняк", 18, new UniversityEducationStrategy("Прикладная математика", 2)));
        students.add(new Student("Дмитрий Попов", 22, new UniversityEducationStrategy("Экономика", 4)));
        
        System.out.println("=== УЧЕБНЫЙ ПРОЦЕСС ===");
        for (Student student : students) {
            student.studyProcess();
        }
        
        System.out.println("\n=== МНОГОЛЕТНЕЕ ОБУЧЕНИЕ ===");
        
        Student schoolStudent = students.get(2);
        Student universityStudent = students.get(4); 
        
        System.out.println("\n" + schoolStudent.getName() + " - полный школьный цикл:");
        for (int year = 0; year < 3; year++) {
            schoolStudent.studyProcess();
        }
        
        System.out.println("\n" + universityStudent.getName() + " - до окончания университета:");
        for (int year = 0; year < 3; year++) {
            universityStudent.studyProcess();
        }
    }
}