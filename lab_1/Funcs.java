public class Funcs{

public static int Fact(int arg){

    int res = 1;

    for(int i = 1; i<=arg; i++){
    
        res*=i;

    }

    return res;
}
    
public static double  cosTaylor(double arg1, int k){ 
    
    if(k<=0) throw new IllegalArgumentException("Degree must be natural!");
    
    double res = 1;
    double member = 1;
    double accuracy = Math.pow(10, -1 * k);
    int xDegree = 2;
    byte pre = 1;
    
    while(arg1 > 360){
         arg1 -= 360;
    }  
    while(arg1 < -2 * 360){
         arg1 += 2 * 360;
    }


    while(Math.abs(member) >= Math.abs(accuracy)){

        member = 1;

        pre*=-1;
        member*=pre;

        for(int i = 1; i <= xDegree; i++){

          member *= Math.toRadians(arg1);
          member /= i;   

        }
       
        
        xDegree += 2;
        
        res += member;

    }

    return res;
    }
}