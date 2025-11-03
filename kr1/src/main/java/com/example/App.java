package com.example;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;


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
            
            Writer.printHotelsByCity(hotels);
            
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка формата данных: " + e.getMessage());
        }
    }
}
