package TimeAndSpaceComplexity;

public class TimeAndSpaceComplexity{
    public static void bubbleSort(int[] arr) {
    int n= arr.length;
    for(int i=1; i<= n-1; i++){
        for(int j=0; j<= n-1-i; j++){
            if(isSmaller(arr, j+1, j)) swap(arr, j+1, j);
        }
    }
    
  }


  public static void selectionSort(int[] arr) {
    int n= arr.length; 
    for(int i=0; i<n-1; i++){
      int min=i;    
      for(int j=i+1; j<=n-1; j++){
          if(isSmaller(arr, j, min)) min=j;
      }  
      swap(arr, i, min);
    }
    
  }

  public static void insertionSort(int[] arr) {
    int n=arr.length;  
    for(int i=1; i<n; i++){
        for(int j=i-1; j>=0; j--){
            if(isGreater(arr, j, j+1)){
              swap(arr, j, j+1);  
            }else break; 
        }
    }
    
  }


  public static int[] mergeTwoSortedArrays(int[] a, int[] b) {
    int[] sortedArray = new int[a.length + b.length];

    int i = 0;
    int j = 0;
    int x = 0;
    while (i < a.length && j < b.length) {
      if (a[i] <= b[j]) {
        sortedArray[x] = a[i];
        i++;
        x++;
      }
      else if (a[i] > b[j]) {
        sortedArray[x] = b[j];
        j++;
        x++;
      }
    }

    while (i < a.length) {
      sortedArray[x] = a[i];
      i++;
      x++;
    }
    while (j < b.length) {
      sortedArray[x] = b[j];
      j++;
      x++;
    }

    return sortedArray;
  }

   public static int[] mergeSort(int[] arr, int lo, int hi) {
     if(lo == hi){
        int[] ba=new int[1];
        ba[0]=arr[lo];
        return ba;
     } 
      
     int mid= (lo+hi)/2;
     int[] arr1=mergeSort(arr, lo, mid);
     int[] arr2=mergeSort(arr, mid+1, hi);
     
     int[] ans=mergeTwoSortedArrays(arr1, arr2);
     
     return ans;
  }

   public static void partition(int[] arr, int pivot){
    // i to arr.length-1 -> unknown
    // 0 to j-1 -> <=pivot
    // j to i-1 -> > pivot
      
    int i=0;
    int j=0;
    while(i<arr.length){
        if(arr[i] > pivot){
            i++;
        }
        else{
            swap(arr, i, j);
            i++;
            j++;
        }
    }
    
  }


  public static void quickSort(int[] arr, int lo, int hi) {
    if(lo > hi) return;
    
    int pivot = arr[hi];
    int pi = partition(arr, pivot, lo, hi);
    quickSort(arr, lo, pi-1);
    quickSort(arr, pi+1, hi);
  }

  public static int partition(int[] arr, int pivot, int lo, int hi) {
    System.out.println("pivot -> " + pivot);
    int i = lo, j = lo;
    while (i <= hi) {
      if (arr[i] <= pivot) {
        swap(arr, i, j);
        i++;
        j++;
      } else {
        i++;
      }
    }
    System.out.println("pivot index -> " + (j - 1));
    return (j - 1);
  }

  public static int quickSelect(int[] arr, int lo, int hi, int k) {
    int pivot = arr[hi];
    int pi = partition(arr, pivot, lo, hi);
    
    if(k < pi){
        return quickSelect(arr, lo, pi-1, k); 
    }else if(k > pi){
        return quickSelect(arr, pi+1, hi, k);
    }else{
        return arr[pi];
    }
  }

  public static void countSort(int[] arr, int min, int max) {
    int n=arr.length;  
    int range= max-min+1;
    int[] freqArr=new int[range];
    
    for(int i=0; i<n; i++){
        int val = arr[i];
        freqArr[val-min] += 1;
    }
    
    for(int i=1; i<freqArr.length; i++){
        freqArr[i] += freqArr[i-1];
    }
    
    int[] ans=new int[n];
    for(int i=n-1; i>=0; i--){
        int pos = freqArr[arr[i]-min];
        int idx = pos-1;
        ans[idx] = arr[i];
        
        freqArr[arr[i]-min]--;
    }
    
    for(int i=0; i<n; i++){
        arr[i]=ans[i];
    }
  }

  public static void radixSort(int[] arr) {
    int max = Integer.MIN_VALUE;
    for(int val: arr){
        max = Math.max(max, val);
    }
    
    int exp = 1;
    while(exp <= max){
        countSort(arr, exp);
        exp *= 10;
    }
  }

  public static void countSort(int[] arr, int exp) {
    int n=arr.length;  
    
    int[] freqArr=new int[10];
    
    for(int i=0; i<n; i++){
        int val = arr[i];
        freqArr[val/exp % 10] += 1;
    }
    
    for(int i=1; i<freqArr.length; i++){
        freqArr[i] += freqArr[i-1];
    }
    
    int[] ans=new int[n];
    for(int i=n-1; i>=0; i--){
        int pos = freqArr[arr[i]/exp % 10];
        int idx = pos-1;
        ans[idx] = arr[i];
        
        freqArr[arr[i]/exp % 10]--;
    }
    
    for(int i=0; i<n; i++){
        arr[i]=ans[i];
    }
    
    
    System.out.print("After sorting on " + exp + " place -> ");
    print(arr);
  }


  public static void sortDates(String[] arr) {
    countSort(arr, 1000000, 100, 32); //days range=31-1+2  
    countSort(arr, 10000, 100, 13); //months range=12-1+2
    countSort(arr, 1, 10000, 2501); //years
  }

  public static void countSort(String[] arr,int div, int mod, int range) {
    int n=arr.length;  
    
    int[] freqArr=new int[range];
    
    for(int i=0; i<n; i++){
        int val = Integer.parseInt(arr[i],10);
        freqArr[val/div % mod] += 1;
    }
    
    for(int i=1; i<freqArr.length; i++){
        freqArr[i] += freqArr[i-1];
    }
    
    String[] ans=new String[n];
    for(int i=n-1; i>=0; i--){
        int pos = freqArr[Integer.parseInt(arr[i],10)/div % mod];
        int idx = pos-1;
        ans[idx] = arr[i];
        
        freqArr[Integer.parseInt(arr[i],10)/div % mod]--;
    }
    
    for(int i=0; i<n; i++){
        arr[i]=ans[i];
    }
    
   
  }

   public static void sort01(int[] arr){
    int i=0; 
    int j=0;
    
    while(i<arr.length){
        if(arr[i]==1) i++;
        else{
            swap(arr, i, j);
            i++;
            j++;
        } 
    }
  }

  public static void sort012(int[] arr){
    int i=0;
    int j=0;
    int k=arr.length-1;
    
    while(i<=k){
        if(arr[i]==0){
          swap(arr,i,j);    
          i++;
          j++;
        }else if(arr[i]==1){
           i++; 
        }else{
           swap(arr, i, k);
           k--;
        }
    }
  }

  public static void targetSumPair(int[] arr, int tar){
   Arrays.sort(arr);      
      
   int i=0;
   int j=arr.length-1;
   
   while(i<j){
      if(arr[i] + arr[j] < tar){
          i++;
      } else if(arr[i] + arr[j] > tar){
          j--;
      } else{
          System.out.println(arr[i]+", "+arr[j]);
          i++;
          j--;
      }
   }

  }

  

}