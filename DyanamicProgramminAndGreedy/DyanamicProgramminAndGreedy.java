package DyanamicProgramminAndGreedy;
import java.util.Scanner;

public class DyanamicProgramminAndGreedy{
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in); 
        int n=sc.nextInt();
        System.out.println(fibDP(n, new int[n+1]));
      }
      
      public static int fib(int n){
        if(n==0 || n==1) return n;
       
        System.out.println("hello "+n);
        int ans = fib(n-1) + fib(n-2); 
        return ans;
      }
      
      public static int fibDP(int n, int[] ansBank){
         if(n==0 || n==1) return n;
         else if(ansBank[n]!=0) return ansBank[n];
         
         System.out.println("hello "+n);
         int ans = fibDP(n-1, ansBank) + fibDP(n-2, ansBank); 
         ansBank[n]=ans;
         
         return ans;
      }



      public static int climbStairsMemo(int n, int[] ansbank){
        if(n==0) return 1;
        else if(n<0) return 0;
        else if(ansbank[n] != 0) return ansbank[n];
        
        
        int ans= climbStairsMemo(n-1, ansbank)
                 + climbStairsMemo(n-2, ansbank)
                 + climbStairsMemo(n-3, ansbank);
                 
        ansbank[n]=ans;
        return ans;
     } 

     public static int climbStairsTab(int n){
        int[] paths=new int[n+1];
        paths[0]=1;
        
        for(int i=1; i<=n; i++){
           if(i==1) paths[i]=paths[i-1]; 
           else if(i==2) paths[i]=paths[i-1]+paths[i-2];
           else paths[i]=paths[i-1]+paths[i-2]+paths[i-3];
        }
        
        return paths[n];
    }


    public static int climbStairsWithVariableJumps(int n,int[] jumps){
        int[] ans=new int[n+1];
        
        ans[n]=1;
        
        for(int i=n-1; i>=0; i--){
            for(int j=1; j<=jumps[i] && i+j < ans.length; j++){
                ans[i] += ans[i+j];
            }
        }
        
        return ans[0];
    }

    public static int climbStairsWithMinJumps(int n,int[] jumps){
        Integer[] minMoves=new Integer[n+1];
        
        minMoves[n]=0;
       
        for(int i=n-1; i>=0; i--){
           if(jumps[i]!=0){
               
            int min = Integer.MAX_VALUE;
               for(int j=1; j<= jumps[i] && i+j < minMoves.length; j++){
                   if(minMoves[i+j]!=null)
                   min = Math.min(min, minMoves[i+j]);
               }
            
            if(min!=Integer.MAX_VALUE)   
             minMoves[i]=min+1; 
            
           }
        }
        return minMoves[0];
    }


    public static int mazeMinCost(int[][] maze,int m,int n){
          
        int[][] ans=new int[m][n];
        ans[m-1][n-1]=maze[m-1][n-1];
        
        for(int j=n-2; j>=0; j--){
           ans[m-1][j] = maze[m-1][j] + ans[m-1][j+1];
        }
        
        for(int i=m-2; i>=0; i--){
            ans[i][n-1] = maze[i][n-1] + ans[i+1][n-1];
        }
        
        for(int i=m-2; i>=0; i--){
            for(int j=n-2; j>=0; j--){
                ans[i][j] = maze[i][j] + Math.min(ans[i+1][j], ans[i][j+1]);
            }
        }
        
        return ans[0][0];
    }

    public static int goldmine(int[][] maze,int m,int n){
          
        int[][] ans=new int[m][n];
        
        for(int i=0; i<m; i++){
            ans[i][n-1]=maze[i][n-1];
        }
        
        for(int j=n-2; j>=0; j--){
            for(int i=0; i<m; i++){
                if(i==0)
                ans[i][j]= maze[i][j] + Math.max(ans[i][j+1], ans[i+1][j+1]);
                
                else if(i==m-1)
                ans[i][j]= maze[i][j] + Math.max(ans[i][j+1], ans[i-1][j+1]);
                
                else
                ans[i][j]= maze[i][j] +Math.max(ans[i][j+1],Math.max(ans[i-1][j+1], ans[i+1][j+1]));
            }
        }
        
        int max=0;
        for(int i=0; i<m; i++){
           max = Math.max(max, ans[i][0]); 
        }
        return max;
    }  
   

    public static boolean targetSumSubsets(int[] arr, int tar) {
        
        boolean[][] canFormSum = new boolean[arr.length+1][tar+1];
        
        for(int i=0; i<canFormSum.length; i++){
            canFormSum[i][0] = true;
        }
           
           
        for(int i=1; i<canFormSum.length; i++){
            for(int j=1; j<canFormSum[0].length; j++){
                
                 if(arr[i-1] > j ) canFormSum[i][j]=canFormSum[i-1][j];
                 else if(canFormSum[i-1][j] || canFormSum[i-1][j-arr[i-1]])
                    canFormSum[i][j]=true; 
            }
        }
        
        return canFormSum[canFormSum.length-1][canFormSum[0].length-1];
     }


     public static int CoinChangeCombination(int[] arr, int tar) {
        
        int[] ans=new int[tar+1];
        ans[0]=1;
        
        for(int i=0; i<arr.length; i++){
            for(int j=1; j<ans.length; j++){
              
              if(j >= arr[i] && ans[j-arr[i]]!=0){
                 ans[j] += ans[j-arr[i]]; 
              }  
            }
        }
        
        return ans[ans.length-1];
       }


       public static int CoinChangePermutation(int[] arr, int tar) {
        
        int[] ans=new int[tar+1];
        ans[0]=1;
        
        for(int i=1; i<ans.length; i++){ // amt - 0,1,2,3,4,5,6,7
            for(int j=0; j<arr.length; j++){ // coin - 2,3,5,6
                
                if(i >= arr[j] && ans[i-arr[j]]!=0)
                  ans[i] += ans[i-arr[j]]; 
            
                
            }
        }
        
        return ans[ans.length-1];
       }

       public static int zeroOneKnapsack(int[] values, int[] wt, int n,int cap) {
        
        int[][] ans=new int[n+1][cap+1];
        
        for(int i=1; i<ans.length; i++){
            for(int j=1; j<ans[0].length; j++){
                
                ans[i][j] = ans[i-1][j];
                if(j >= wt[i-1]){
                    ans[i][j] = Math.max(ans[i-1][j], values[i-1]+ans[i-1][j-wt[i-1]]);
                }
            }
        }
        
        return ans[ans.length-1][ans[0].length-1];
     }


     public static int unboundedKnapsack(int[] values, int[] wt, int n,int cap) {
        
        int[] ans=new int[cap+1];
        
        for(int i=0; i<n; i++){
          for(int j=1; j<ans.length; j++){
              
              if(j >= wt[i]) ans[j] = Math.max(ans[j], values[i]+ans[j-wt[i]]);
          }  
        }
        
        return ans[ans.length-1];
     }


     public static int countBinaryStrings(int n){
         int[] b0=new int[n+1]; 
         int[] b1=new int[n+1];

         b0[1]=1;
         b1[1]=1;

         for(int i=2; i<= n-1; i++){
             b0[i]=b1[i-1];
             b1[i]=b0[i-1] + b1[i-1];
         }

         return b0[n]+b1[n];
     }

     public static int countBinaryStringsWithVar(int n){
        int prev0=1, prev1=1;  
        
        for(int i=0; i<= n-2; i++){
            int new0=prev1;
            int new1=prev0 + prev1;

            prev0=new0;
            prev1=new1;
        }

        return prev0+prev1;
    }

    public static long arrangeBuildings(int n){
        long prev0=1, prev1=1;  
        
        for(int i=2; i<= n; i++){
            long new0=prev1;
            long new1=prev0 + prev1;

            prev0=new0;
            prev1=new1;
        }

        long temp=prev0+prev1;
        long res=temp*temp;

        return res;
    }


    
    
     
}