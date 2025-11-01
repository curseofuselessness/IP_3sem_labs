package com.example;

public class Student {
    private String name;
    private int age;
    private EducationStrategy educationStrategy;
    
    public Student(String name, int age, EducationStrategy strategy) {
        this.name = name;
        this.age = age;
        this.educationStrategy = strategy;
    }
    
    public void studyProcess() {
        System.out.println("--- " + name + " (" + educationStrategy.getDetails() + ") ---");
        attendClasses();
        educationStrategy.learn();
        takeExams();
        if (educationStrategy.canContinue()) {
            educationStrategy.continueEducation();
        }
        System.out.println("--------------------------------");
    }
    
    private void attendClasses() {
        System.out.println(name + " посещает занятия");
    }
    
    private void takeExams() {
        System.out.println(name + " сдает экзамены");
    }

    public void setEducationStrategy(EducationStrategy strategy) {
        this.educationStrategy = strategy;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
}