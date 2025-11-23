package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class GradeBook {

    public studentInfo student;
    public sessionInfo session;

    class studentInfo {

        public String firstName;
        public String middleName;
        public String lastName;

        public int year;
        public int group;
        
        public String specialization;

        @Override
        public String toString() {

            return "FIO: " +
            lastName + " " +
            firstName + " " +
            middleName + "\n" +
            "Year: " + String.valueOf(year) + "\n" +
            "Group: " + String.valueOf(group) + "\n";

        }

    }

    class sessionInfo {
        
        public Map<String, Integer> grades = new HashMap<>();

        public boolean isExcellentStudent() {
            boolean answer = true;
            
            for(Map.Entry<String, Integer> grade : grades.entrySet()) {
                if(grade.getValue() < 9) {
                    answer = false;
                    break;
                }
            }

            return answer;
        }

    }

    
}

class ReadWrite {

    static ArrayList<GradeBook> read(String filename) {
        ArrayList<GradeBook> out = new ArrayList<>();
        
        Scanner scanner = new Scanner(filename);

        while (scanner.hasNextLine()) {
            
            String line = scanner.nextLine();
            System.out.println(line);
            String[] words = line.split(":");
            
            String field = words[0];
            String info = words[1];

            GradeBook gradeBook = new GradeBook();
                
            switch (field) {
                case "FIO":
                    String[] FIO = info.trim().split(" ");
                    gradeBook.student.lastName = FIO[0];
                    gradeBook.student.firstName = FIO[1];
                    gradeBook.student.middleName = FIO[2];
                    break;
                case "Specialization":
                    gradeBook.student.specialization = info;
                    break;
                case "Year":
                    gradeBook.student.year = Integer.parseInt(info);
                    break;
                 case "Group":
                    gradeBook.student.group = Integer.parseInt(info);
                    break;    
                case "grade":
                    String[] grade = info.trim().split(" ");
                    gradeBook.session.grades.put(grade[0], Integer.parseInt(grade[1]));
                case "":
                    out.add(gradeBook);
                    gradeBook = new GradeBook();
                default:
                    throw new AssertionError();
            }
        

        }

        return out;
    }

    static void writeNiceStudents(ArrayList<GradeBook> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            for (GradeBook studentGradeBook : students) {
                if(studentGradeBook.session.isExcellentStudent()) {
                    writer.write(studentGradeBook.student.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class App {
    public static void main( String[] args ) {

        ArrayList<GradeBook> students = ReadWrite.read("input.txt");

        ReadWrite.writeNiceStudents(students);

    }
}
