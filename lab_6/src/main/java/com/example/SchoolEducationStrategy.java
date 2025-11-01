package com.example;

public class SchoolEducationStrategy implements EducationStrategy {
    private int grade;
    private boolean finished;
    
    public SchoolEducationStrategy(int grade) {
        this.grade = grade;
        this.finished = false;
    }
    
    @Override
    public void learn() {
        System.out.println("Изучаю школьные предметы в " + grade + " классе");
    }
    
    @Override
    public boolean canContinue() {
        return grade < 11 && !finished;
    }
    
    @Override
    public void continueEducation() {
        grade++;
        System.out.println("Перехожу в " + grade + " класс");
        if (grade == 11) {
            finished = true;
            System.out.println("Заканчиваю школу!");
        }
    }
    
    @Override
    public String getDetails() {
        return "Школьник, " + grade + " класс";
    }
    
    public int getGrade() { return grade; }
}