import java.util.*;

public class FibonacciTillN{
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n=scn.nextInt();

        
        int a=0, b=1, next=0;
        for(int i=1; i<= n; i++){
            next=a+b;
            System.out.println(a);
            a=b;
            b=next;
        }
        

    }
}