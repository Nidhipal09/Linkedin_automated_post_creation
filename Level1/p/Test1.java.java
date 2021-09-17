public class Test1{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        int sv=0, ev=1;
        for(int i=0; i< n; i++){
            if(i<= n/2){
               for(int j=0; j< n; j++){
                 if(j==0){
                     System.out.print(n*sv+1+" ");
                 }else if(j==n-1){
                     System.out.print(n*ev+" ");
                 }else{
                     System.out.print("* ");
                 }
                 sv += 2;
                 ev += 2;  
               }
            }else{
               if(i == n/2+1){
                   sv--;
                   ev--;
               } 
               for(int j=0; j< n; j++){
                 if(j==0){
                     System.out.print(n*sv+1+" ");
                 }else if(j==n-1){
                     System.out.print(n*ev+" ");
                 }else{
                     System.out.print("* ");
                 }
                 sv -= 2;
                 ev -= 2;  
               }
            }

            System.out.println();
        }
    }
}