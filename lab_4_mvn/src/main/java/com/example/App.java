package com.example;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner Sc = new Scanner(System.in);
        
        String text = Sc.nextLine();
        
        System.out.println(decrypt(text));
    }

    public static char[] decrypt( String args)
    {

        char[] textChar = args.toCharArray();
        
        char[] decryptedText = new char[args.length()];

        int step = args.length() / 3;

        int stepF = 0;
        int stepS = 0;

        if(args.length() % 3 == 2){
            stepF = 1;
            stepS = 1;
        }
         if(args.length() % 3 == 1){
            stepF = 1;
            stepS = 0;
        }
        
        
        int textI = 0;
            for(int i = 0; i < step + stepF; i++){ // 147258369
                decryptedText[textI] = textChar[i]; 
                textI+=3;
            }
            textI = 1;
            for(int i = step  + stepF; i < step*2 + stepS  + stepF; i++){ // 147258369
                decryptedText[textI] = textChar[i]; 
                textI+=3;   
            }
            textI = 2;
            for(int i = step*2 + stepS + stepF; i < args.length(); i++){ // 147258369
                decryptedText[textI] = textChar[i]; 
                textI+=3;   
            }
            

        
        

        return decryptedText;
    }
}
