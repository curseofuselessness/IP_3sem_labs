package com.example;

public class UniversityEducationStrategy implements EducationStrategy {
    private String major;
    private int year;
    private boolean graduated;
    
    public UniversityEducationStrategy(String major, int year) {
        this.major = major;
        this.year = year;
        this.graduated = false;
    }
    
    @Override
    public void learn() {
        System.out.println("Изучаю специальность '" + major + "' на " + year + " курсе");
    }
    
    @Override
    public boolean canContinue() {
        return year < 4 && !graduated;
    }
    
    @Override
    public void continueEducation() {
        year++;
        System.out.println("Перехожу на " + year + " курс");
        if (year == 4) {
            graduated = true;
            System.out.println("Получаю диплом по '" + major + "'!");
        }
    }
    
    @Override
    public String getDetails() {
        return "Студент, " + year + " курс, " + major;
    }
    
    public String getMajor() { return major; }
    public int getYear() { return year; }
}