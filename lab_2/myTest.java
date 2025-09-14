// Временный фикс - используй старый добрый JUnit 4
import org.junit.Test;
import static org.junit.Assert.*;

public class myTest{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String stringHeight = sc.nextLine();
        String stringWidth = sc.nextLine();
        
        int height = Integer.parseInt(stringHeight);
        int width = Integer.parseInt(stringWidth);

        int[][] matrix = new int[width+1][height+1];

        int Count1 = 0;
        int Count2 = 0;
        int maxElement = -1;
        int tempMaxElement = -1;

        for(int i = 0; i < height; i++){

            for(int j = 0; j < width; j++){

                matrix[j][i] = random.nextInt(11);

                System.out.print(matrix[j][i]);
                System.out.print("\t");

                if(j > 0){
                    
                    if(matrix[j][i] >= matrix[j-1][i]){
                      Count1++;
                    }
                    if(matrix[j][i] <= matrix[j-1][i]){
                      Count2++;
                    }
                    
                }
                
                if(matrix[j][i] > tempMaxElement){
                    tempMaxElement = matrix[j][i];
                }

            }

            if(Count1 == width - 1 || Count2 == width - 1){
            
                maxElement = tempMaxElement;

            } 
            
            tempMaxElement = -1;

            System.out.print("\n");
            System.out.print("\n");

            Count1 = 0;
            Count2 = 0;

        }
    
        System.out.println(maxElement);
        

        


    }

}