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
    double accuracy = Math.pow(10, -1*k);
    int xDegree = 2;
    int memberFactorial = 2;
    byte pre = 1;

    while(Math.abs(member) >= Math.abs(accuracy) ){

        member = 1;

        pre*=-1;
        member*=pre;
        member*=Math.pow(Math.toRadians(arg1), xDegree);
        member/=Fact(memberFactorial);
        
        xDegree+=2;
        memberFactorial+=2;
        
        res += member;

     if(Math.abs(member) > 1) System.out.println(member);
        
    }

    return res;
    }
}