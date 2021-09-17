import java.util.*;
  
  public class IsPrime{
  
  public static void main(String[] args) {
      Scanner scn = new Scanner(System.in);
      int t=scn.nextInt();

      for(int i=1; i<=t; i++){
        int n=scn.nextInt();
        
        int status=0;
        for(int div=2; div*div <= n; div++){
            if(n%div == 0){
                status=1;
                System.out.println("not prime");
                break;
            }
        }

        if(status==0) System.out.println("prime");

      }
      
   }
  }
