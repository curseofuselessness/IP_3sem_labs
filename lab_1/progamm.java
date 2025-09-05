import java.util.Scanner;


public class progamm{

    static double x, k;
     public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        String x_string = sc.nextLine();
        String k_string = sc.nextLine();

        x = Double.parseDouble(x_string);
        k = Double.parseDouble(k_string);

        System.out.print(k);
        System.out.print("Taylor cos: ");
         System.out.printf( "%.3f%n", func.CosTaylor(x, k));
        System.out.print("Java cos: "); 
        System.out.printf( "%.3f%n",  Math.cos(Math.toRadians(x)));
    }
}
