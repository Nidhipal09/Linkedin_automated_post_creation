import java.util.*;

public class AllPrimes{
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int a=scn.nextInt();
        int b=scn.nextInt();

        for(int n=a; n<=b; n++){

            int status=0;
            for(int div=2; div*div <= n; div++){
                if(n%div == 0){
                    status=1;
                    break;
                }
            }

            if(status==0) System.out.println(n);
        }
    }
}