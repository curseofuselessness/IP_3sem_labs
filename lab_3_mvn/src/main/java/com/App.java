package com;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "enter text: ");

        Scanner sc = new Scanner(System.in);
        
        String Text = "";

        while(true){

            String Line = sc.nextLine();
            Text += (" " + Line);
            
            if(Line == ""){
                break;
            }

        }

        System.out.println( "founded words: ");

        for(String word : splitToWords(Text)) {
              System.out.println(word);
        }

      

    }
    public static Vector<String> splitToWords(String line){
   
    Pattern pattern = Pattern.compile("[^\\s,.]+");
    
    Matcher matcher = pattern.matcher(line);

    Vector<String> wordsArray = new Vector<>();

    while (matcher.find()) {
        String word = matcher.group();
        
        if(!word.matches("\\d+")) {
            wordsArray.add(word);
        }
    }
    return wordsArray;  
}
}
