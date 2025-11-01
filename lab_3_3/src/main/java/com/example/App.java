package com.example;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Formatter {

    public String getText(Scanner s) {
        
        String text = "";

        while(s.hasNextLine()) {
           text += s.nextLine();
        }
        
        return text;
    }

    public String[] getWords(String text) {

        String regexWord = "[a-zA-Z]+";
        String regexNumber = "/d+";

        Pattern patternWord = Pattern.compile(regexWord);
        Pattern patternNumber = Pattern.compile(regexNumber);
        Matcher matcher = pattern.matcher(text);

        while()

    }
}


public class App 
{

    public static void main( String[] args )
    {
        Scanner s = new Scanner(("input.txt"));


    }
}
