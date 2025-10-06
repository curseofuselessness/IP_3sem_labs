package com.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab_5_A {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Double> current = new ArrayList<>();
        List<Double> voltage = new ArrayList<>();

        System.out.print("enter size of array: ");
        int n = sc.nextInt();

        System.out.println("enter amperage values:");
        for (int i = 0; i < n; i++){
           current.add(sc.nextDouble());
        }

        System.out.println("enter voltage values:");
        for (int i = 0; i < n; i++){
           voltage.add(sc.nextDouble());
        }

        double R = approximateResistance(current, voltage);

        System.out.printf("approximated R: %.4f Om\n", R);

    }

    public static double approximateResistance(List<Double> I, List<Double> U) {

        double sumIU = 0.0;
        double sumI2 = 0.0;

        for (int i = 0; i < I.size(); i++){

            sumIU += I.get(i) * U.get(i);
            sumI2 += I.get(i) * I.get(i);

        }

        return sumIU / sumI2;
    }
}
