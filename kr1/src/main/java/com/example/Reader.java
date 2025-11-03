package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public static List<Hotel> readHotels(String filename) throws IOException {
        List<Hotel> hotels = new ArrayList<>(); 
        
        List<String> lines = Files.readAllLines(Paths.get(filename)); 
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            
            String[] parts = line.split(" ");
            if (parts.length < 3) throw new IllegalArgumentException("Invalid input");

            int stars = Integer.parseInt(parts[parts.length - 1]);

            String city = parts[0];
            
            StringBuilder name = new StringBuilder();
            for (int i = 1; i < parts.length - 1; i++) {
                if (i > 1) name.append(" ");
                name.append(parts[i]);
            }
            
            hotels.add(new Hotel(name.toString(), city, stars));
        }
        
        return hotels;
    }
}