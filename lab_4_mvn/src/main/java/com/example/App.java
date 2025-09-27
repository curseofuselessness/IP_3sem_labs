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

        int step;

        if(args.length() % 3 == 0){
            step = args.length() / 3;
        }
        else{
            System.err.println("ERROR: invalid input");
            String er = "ERROR";
            char[] erChar = er.toCharArray();
            return erChar;
        }
        
       
        int decryptI = 0;
        int argsI = 0;

        while(argsI != args.length() - 1){  // 147258369
            decryptedText[decryptI++] = textChar[argsI];
            argsI += 3;
            if(argsI / (args.length() - 0) >= 1){
                 argsI %= (args.length() - 0);
                 argsI++;
            }
            else{
                argsI %= (args.length() - 0);
            }
            
            
            
        }
        decryptedText[decryptI++] = textChar[argsI];
        

        return decryptedText;
    }
}
