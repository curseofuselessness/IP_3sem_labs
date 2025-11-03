package com.example;

import java.util.List;

public class Writer {
     public static void printHotelsByCity(List<Hotel> hotels) {
        System.out.println("ОТЕЛИ ПО ГОРОДАМ (алфавитный порядок)");
        System.out.println("======================================");
        
        String currentCity = "";
        for (Hotel hotel : hotels) {
            // Выводим заголовок города когда он меняется
            if (!hotel.getCity().equals(currentCity)) {
                currentCity = hotel.getCity();
                System.out.println("\n--- " + currentCity + " ---");
            }
            
            System.out.printf("  Звёзд: %d - %s%n", hotel.getStarsAmount(), hotel.getName());
        }
    }

    public static void printAllHotels(List<Hotel> hotels) {
        System.out.println("ВСЕ ОТЕЛИ:");
        System.out.println("==========");
        
        for (Hotel hotel : hotels) {
            System.out.printf("%s, %s - %d звезд(ы)%n", 
                hotel.getCity(), hotel.getName(), hotel.getStarsAmount());
        }
        
        System.out.println("==========");
        System.out.println("Всего отелей: " + hotels.size());
    }

     public static void printHotelsByCityName(List<Hotel> hotels, String cityName) {
        System.out.println("Отели в городе " + cityName + ":");
        System.out.println("-------------------");
        
        boolean found = false;
        
        for (Hotel hotel : hotels) {
            if (hotel.getCity().equalsIgnoreCase(cityName)) {
                System.out.printf("Звёзд: %d - %s%n", hotel.getStarsAmount(), hotel.getName());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("Отелей не найдено");
        }
    }

     public static void printCitiesByHotelName(List<Hotel> hotels, String hotelName) {
        System.out.println("Города с отелем '" + hotelName + "':");
        System.out.println("-------------------");
        
        boolean found = false;
        
        for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                System.out.printf("Звёзд: %d - %s%n", hotel.getStarsAmount(), hotel.getCity());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("Отель не найден");
        }
    }

}
