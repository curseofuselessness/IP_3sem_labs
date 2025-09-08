import java.util.Scanner;


public class Programm{

    static double x;
    static int k;
     public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        
        String x_string = sc.nextLine();
        String k_string = sc.nextLine();

        x = Double.parseDouble(x_string);
        k = Integer.parseInt(k_string);
         
       
        try {
             System.out.print("Taylor cos: ");
             System.out.printf( "%.3f%n", Funcs.cosTaylor(x, k));
        } catch (Exception e) {
            System.err.println(e);
            return;
        }
         
        System.out.print("Java cos: "); 
        System.out.printf( "%.3f%n",  Math.cos(Math.toRadians(x)));

    }
}
