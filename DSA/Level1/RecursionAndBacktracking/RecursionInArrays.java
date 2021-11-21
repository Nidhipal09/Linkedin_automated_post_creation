package RecursionAndBacktracking;

public class RecursionInArrays {
    
    public static void displayArr(int[] arr, int i){
        if(i<0) return;
        displayArr(arr, i-1);
        System.out.println(arr[i]);
    }

    public static void displayArrReverse(int[] arr, int i) {
        if(i<0) return;
        System.out.println(arr[i]);
        displayArrReverse(arr,i-1);
    }

    public static int maxOfArray(int[] arr, int i){
        if(i==arr.length-1) return arr[i];
        
        int max=maxOfArray(arr, i+1);
        if(arr[i] > max){
            max=arr[i];
        }
        return max;
        
    }

    public static int firstIndex(int[] arr, int i, int x){
        if(i==arr.length) return -1;
        
        if(arr[i]==x) return i;
        return firstIndex(arr, i+1, x);
        
    }

    public static int lastIndex(int[] arr, int i, int x){
        if(i<0) return -1;
        
        if(arr[i]==x) return i;
        return lastIndex(arr, i-1, x);
    }

    public static int[] allIndices(int[] arr, int x, int i, int fsf) {
        if(i==arr.length) return new int[fsf];  
         
        if(arr[i]==x){
          int[] ans=allIndices(arr, x, i+1, fsf+1); 
          ans[fsf]=i;
          return ans;
        }else{
          int[] ans=allIndices(arr, x, i+1, fsf); 
          return ans;
        } 
      
      }

    
}
