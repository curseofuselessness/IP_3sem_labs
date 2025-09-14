package org;

import java.util.Random;
import java.util.Scanner;

public class App{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String stringHeight = sc.nextLine();
        String stringWidth = sc.nextLine();
        
        sc.close();

        int height = Integer.parseInt(stringHeight);
        int width = Integer.parseInt(stringWidth);

        int[][] matrix = new int[width+1][height+1];

        int MaxElement = -1;

        for(int i = 0; i < height; i++){

            for(int j = 0; j < width; j++){

                matrix[i][j] = random.nextInt(11);

                System.out.print(matrix[i][j]);
                System.out.print("\t");

            }
           
            System.out.print("\n");
            System.out.print("\n");

            if(MaxElement < searchMaxEleventIfSequence(matrix, i)){
              
                MaxElement = searchMaxEleventIfSequence(matrix, i);

            }

        }
    
        System.out.println(MaxElement);

        


    }

    public static int searchMaxEleventIfSequence(int[][] Matrix, int NumOfLine){

        int Count1 = 0;
        int Count2 = 0;
        int tempMaxElement = -1;
        int maxElement = -1;

        for(int j = 0; j < Matrix[NumOfLine].length; j++){

            if(j > 0){
                    
                if(Matrix[NumOfLine][j] >= Matrix[NumOfLine][j-1]){
                    Count1++;
                }
                if(Matrix[NumOfLine][j] <= Matrix[NumOfLine][j-1]){
                    Count2++;
                }
                    
            }
                
            if(Matrix[NumOfLine][j] > tempMaxElement){
                tempMaxElement = Matrix[NumOfLine][j];
            }

            }

            if(Count1 == Matrix[NumOfLine].length - 1 || Count2 == Matrix[NumOfLine].length - 1){
            
                maxElement = tempMaxElement;

            } 
            
            return maxElement;

        }
    
        
}