package com.example;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class App 
{

    
    public static void main( String[] args )
    {
        try {

            List<Hotel> hotels = Reader.readHotels("hotel.txt");
            
            hotels.sort(Comparator
                .comparing(Hotel::getCity)                    
                .thenComparing(Hotel::getStarsAmount).reversed() 
            );
            
            System.out.println("Task 1: \n");
            Writer.printHotelsByCity(hotels);
            System.out.println("\n Task 2: \n");
            System.out.println(" ввведите название города: ");

            Scanner sc = new Scanner(System.in);
            String tempCity = sc.nextLine().trim();

            Writer.printHotelsByCityName(hotels, tempCity);

            System.out.println("\n Task 3: \n");
            System.out.println(" ввведите название отеля: ");
            String tempHotel = sc.nextLine().trim();

            Writer.printCitiesByHotelName(hotels, tempHotel);
            
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка формата данных: " + e.getMessage());
        }
    }
}
