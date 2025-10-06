package com.example;

import java.io.InputStream;
import java.util.Scanner;

public class App{

    static class Node{
        int row, col;
        double value;
        Node next;

        Node(int r, int c, double v){
            row = r;
            col = c;
            value = v;
            next = this; 
        }

        void insert(Node newNode) {
            newNode.next = this.next;
            this.next = newNode;
        }
    }

    public static void main(String[] args){

        InputStream is = App.class.getResourceAsStream("/input.txt");
        Scanner sc = new Scanner(is);
       // Scanner sc = new Scanner(new File("/input.txt") );

        double[][] A = null, B = null;

        while (sc.hasNext()) {

            String label = sc.next();

            int rows = sc.nextInt();
            int cols = sc.nextInt();

            double[][] matrix = new double[rows][cols];

            for (int i = 0; i < rows; i++){

                 for (int j = 0; j < cols; j++){
                    matrix[i][j] = sc.nextDouble();
                 }
                    
            }
               

            if (label.equals("A")){
                A = matrix;
            } 
            else {
                if (label.equals("B")) B = matrix;
            }

            }

        Node CA = buildListFromMatrix(A);
        Node CB = buildListFromMatrix(B);

        double[][] S = addMatrices(A, B);
        double[][] P = multiplyMatrices(A, B);

        printMatrix(S, "S = A + B");
        printMatrix(P, "P = A * B");

        System.out.println("\nnotZero A elments:");
        printList(CA);

        System.out.println("\nnotZero B elments:");
        printList(CB);
    }

    public static Node buildListFromMatrix(double[][] matrix) {
        Node head = null;
        Node tail = null;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if (matrix[i][j] != 0){

                    Node node = new Node(i, j, matrix[i][j]);

                    if (head == null){
                        head = node;
                        tail = node;
                    }
                    else{
                        tail.insert(node);
                        tail = node;
                    }
                }
            }
        }

        return head;
    }

    public static double[][] addMatrices(double[][] A, double[][] B) {

        int rows = A.length;
        int cols = A[0].length;

        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                result[i][j] = A[i][j] + B[i][j];
            }
        }
            
                

        return result;
    }

    public static double[][] multiplyMatrices(double[][] A, double[][] B) {

        int rows = A.length;
        int cols = B[0].length;
        int common = A[0].length;
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                for (int k = 0; k < common; k++){
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
           
               
        return result;
    }

    public static void printMatrix(double[][] matrix, String name) {

        System.out.println("\nMatrix " + name + ":");

        for (double[] row : matrix) {

            for (double val : row) {
                System.out.printf("%6.2f ", val);
            }

            System.out.println();
        }
    }

    public static void printList(Node head) {

        if (head == null) {
            System.out.println("List is empty.");

            return;
        }

        Node current = head;

        do {

            System.out.printf("(%d, %d) = %.2f\n", current.row, current.col, current.value);
            current = current.next;

        } while (current != head);
    }
}
