package Basics;

public class GettingStarted {
    public static boolean isPrime(int n) {
        for(int div=2; div*div <= n; div++){
            if(n%div == 0){
               return false;
            }
        }
        return true;
    }

    public static void allPrimesTillN(int a, int b) {
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

    public static void fib(int n) {
        int a=0, b=1, next=0;
        for(int i=1; i<= n; i++){
            next=a+b;
            System.out.println(a);
            a=b;
            b=next;
        }
    }

    public static void printDigits(int n) {
        int count=0, temp=n;
        while(temp>0){
            temp/=10;
            count++;
        }
        
        int div=(int)Math.pow(10, count-1);

        while(div>0){
           System.out.println(n/div);
           n %= div;
           div /= 10;
        }
    }

    public static int inverse(int n) {
        
        int i=1, num=0;

        while(n>0){
            int d=n % 10;
            num += i*Math.pow(10, d-1);
            i++;
            n /= 10;
           
        }
        
        return num;
        
    }

    public static int rotate(int n, int k) {
        
        int count=0, temp=n;
        while(temp>0){
            temp /=10;
            count++;
        }
        
        k %= count;
        if(k<0) k += count;

        int div = (int)Math.pow(10, k);
        int fp = n % div;  // first part
        int sp = n / div;  // second half

        int div1 = (int)Math.pow(10, count);
        int mul = div1/div;
        int num = fp * mul + sp;

        return num;
    }

    public static void GCDandLCM(int a, int b) {
        
        int x=Math.min(a, b);
        int y=Math.max(a, b);

        while(x!=0){  // GCD
            int rem = y % x;
            y = x;
            x = rem;
        }

        System.out.println(y);
        System.out.println(a*b / y);
    }
    
    public static void factorisation(int n) {
        
        for(int i=2; i*i<=n; i++){
            while(n%i == 0){
                System.out.print(i+" ");
                n /= i;
            }
        }
        
        if(n!=1) System.out.print(n);
    }

    public static void benjaminBulbs(int n) {
        
        for(int i=1; i*i <= n; i++){
            System.out.println(i*i);
        }
    }

}
