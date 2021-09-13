import java.util.*;
    
    public class DigitsOfANum{
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n=scn.nextInt();
    
        int temp=n, count=0;

        while(temp!=0){
        temp /= 10; 
        count++;
        }
       
        int div=(int)Math.pow(10,count-1);
        

        while(div!=0){
            System.out.println(n/div);
            n = n % div;
            div /= 10;
        } 
        
     }
    }