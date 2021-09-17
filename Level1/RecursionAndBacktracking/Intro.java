package RecursionAndBacktracking;

public class Intro {

    public static void printDecreasing(int n){
        if(n==0) {return;}
        System.out.println(n);
        printDecreasing(n-1);
    }

    public static void printIncreasing(int n){
        if(n==0) {return; }
        printIncreasing(n-1);
        System.out.println(n);
    }

    public static void pdi(int n){
        if(n==0) {return;}
        System.out.println(n);
        pdi(n-1);
        System.out.println(n); 
     }

     
     public static int factorial(int n){
        if(n==1) return 1;
        return n * factorial(n-1);
    }

    public static int power(int x, int n){
        if(n==0) return 1;
        return x* power(x,n-1);
    }

    public static int powerLog(int x, int n){
        if(n==0) return 1;
        if(n==1) return x;
        int a= powerLog(x, n/2);
        if(n%2 == 0){
         return a*a;
        }else{
          return a*a*x;    
        }
    }

    public static void pzz(int n){
        if(n==0) return ;
        System.out.print(n+" ");
        pzz(n-1);
        System.out.print(n+" ");
        pzz(n-1);
        System.out.print(n+" ");
    }

    public static void toh(int n, int t1, int t2, int t3){    
        if(n==0) return;
        toh(n-1, t1, t3, t2);
        System.out.println(n+"["+t1+" -> "+t2+"]");
        toh(n-1, t3, t2, t1);
    }


}