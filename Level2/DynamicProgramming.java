package Level2;

import java.util.*;

public class DynamicProgramming {
    public static int LongestIncreasingSubsequence(int[] arr) {
        int maxlen = 0;

        int[] ans = new int[arr.length]; // element - longest increasing ss ending at element
        ans[0] = 1;

        for (int i = 1; i < arr.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && ans[j] >= max)
                    max = ans[j];
            }
            ans[i] = max + 1;
        }

        for (int val : ans)
            maxlen = Math.max(val, maxlen);
        return maxlen;
    }

    public static int MaximumSumIncreasingSubsequence(int[] arr) {
        int maxSum = 0;

        int[] ans = new int[arr.length]; // element - longest increasing ss ending at element
        ans[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] >= arr[j] && ans[j] > max)
                    max = ans[j];
            }
            ans[i] = max + arr[i];
        }

        for (int val : ans)
            maxSum = Math.max(val, maxSum);
        return maxSum;
    }

    public static void LongestBitonicSS(int[] a) {
        int n = a.length;

        int[] incre = new int[n];
        for (int i = 0; i < incre.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (a[j] <= a[i]) {
                    max = incre[j] > max ? incre[j] : max;
                }
            }
            incre[i] = max + 1;

        }

        int[] decre = new int[n];
        for (int i = a.length - 1; i >= 0; i--) {
            int min = 0;
            for (int j = a.length - 1; j > i; j--) {
                if (a[j] <= a[i]) {
                    min = decre[j];
                }
            }
            decre[i] = min + 1;
        }

        int omax = 0;
        for (int i = 0; i < a.length; i++) {
            omax = incre[i] + decre[i] - 1 > omax ? incre[i] + decre[i] - 1 : omax;
        }
        System.out.println(omax);
    }
   
    public static class Bridge implements Comparable<Bridge> {
        int n, s;
    
        public Bridge(int n, int s) {
          this.n = n;
          this.s = s;
        }
    
        public int compareTo(Bridge o) {
          if (this.n != o.n) return this.n - o.n;
          else return this.s - o.s;
        }
      }
    
      public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int no = scn.nextInt();
        Bridge[] a = new Bridge[no];
        for (int i = 0; i < no; i++) {
          int n = scn.nextInt();
          int s = scn.nextInt();
          a[i] = new Bridge(n, s);
        }
    
        Arrays.sort(a);
    
        int[] ans = new int[no];
        int omax = 0;
    
        for (int i = 0; i < ans.length; i++) {
          int max = 0;
          for (int j = 0; j < i; j++) {
            if (a[j].s <= a[i].s) {
              max = ans[j] > max ? ans[j] : max;
            }
          }
          ans[i] = max + 1;
          omax = ans[i] > omax ? ans[i] : omax;
        }
        System.out.println(omax);
      }

      
    
}
