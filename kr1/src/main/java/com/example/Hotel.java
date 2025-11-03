package com.example;

import java.util.Comparator;


public class Hotel implements Comparable<Hotel> {

    private int starsAmount;
    
    private String name;
    private String city;

    public Hotel() {
    }

    public Hotel(String Name, String City, int Stars) {
        this.name = Name;
        this.city = City;
        setStarsAmount(Stars);
    }

    // ГЕТТЕРЫ 
    public int getStarsAmount() {
        return starsAmount;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    // CЕТТЕРЫ
    public void setStarsAmount(int starsAmount) {
        if (starsAmount >= 2 && starsAmount <= 4) {
            this.starsAmount = starsAmount;
        } else {
            throw new IllegalArgumentException("Invalid stars amount: " + starsAmount + ". Stars must be between 2 and 4.");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    @Override
    public int compareTo(Hotel other) {
        return Integer.compare(this.starsAmount, other.starsAmount);
    }
    // методы для сортировки по  полям
    public static Comparator<Hotel> sortByName() {
        return Comparator.comparing(Hotel::getName);
    }

    public static Comparator<Hotel> sortByCity() {
        return Comparator.comparing(Hotel::getCity);
    }

    public static Comparator<Hotel> sortByStarsDesc() {
        return Comparator.comparing(Hotel::getStarsAmount).reversed();
    }
}