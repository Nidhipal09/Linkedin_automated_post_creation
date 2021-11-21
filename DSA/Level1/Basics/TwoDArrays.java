package Basics;


public class TwoDArrays {
    public static void matrixMultiplication(int[][] a1, int[][] a2) {
        int m1 = a1.length;
        int n1 = a1[0].length;

        int m2 = a2.length;
        int n2 = a2[0].length;

        if (n1 != m2)
            return;

        int[][] ans = new int[m1][n2];

        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n2; j++) {
                for (int k = 0; k < n1; k++) {
                    ans[i][j] += a1[i][k] * a2[k][j];
                }
            }
        }
    }

    public static void wakanda1(int[][] a) {
        int m = a.length;
        int n = a[0].length;

        for (int j = 0; j < n; j++) {
            if (j % 2 == 0) {
                for (int i = 0; i < m; i++) {
                    System.out.println(a[i][j]);
                }
            } else {
                for (int i = m - 1; i >= 0; i--) {
                    System.out.println(a[i][j]);
                }
            }
        }
    }

    public static void spiralDisplay(int[][] a) {
        int m = a.length;
        int n = a[0].length;

        int minr = 0, minc = 0, maxr = m - 1, maxc = n - 1;

        int tne = m * n; // total no of elements
        int ce = 0; // count of elements
        while (ce < tne) {

            for (int i = minr; i <= maxr && ce < tne; i++) { // left wall
                System.out.println(a[i][minc]);
                ce++;
            }
            minc++;

            for (int j = minc; j <= maxc && ce < tne; j++) { // bottom wall
                System.out.println(a[maxr][j]);
                ce++;
            }
            maxr--;

            for (int i = maxr; i >= minr && ce < tne; i--) { // right wall
                System.out.println(a[i][maxc]);
                ce++;
            }
            maxc--;

            for (int j = maxc; j >= minc && ce < tne; j--) { // top wall
                System.out.println(a[minr][j]);
                ce++;
            }
            minr++;
        }

    }

    public static void exitMatrix(int[][] a) {
        int i = 0, j = 0, dir = 0;

        while (true) {
            dir = (dir + a[i][j]) % 4;

            if (dir == 0) { // east
                j++;
            } else if (dir == 1) { // south
                i++;
            } else if (dir == 2) { // west
                j--;
            } else { // north
                i--;
            }

            if (i < 0) {
                i++;
                break;
            } else if (j < 0) {
                j++;
                break;
            } else if (i == a.length) {
                i--;
                break;
            } else if (j == a.length) {
                j--;
                break;
            }
        }

        System.out.print(i + "\n" + j);
    }

    public static void rotateBy90(int[][] a) {
        int m = a.length;
        int n = a[0].length;

        for(int i=0; i<m; i++){   // transpose
            for(int j=i; j<n; j++){
                 int temp=a[i][j];
                 a[i][j]=a[j][i];
                 a[j][i]=temp;
            }
        }

        int c1=0, c2=n-1;
        while(c1 < c2){     // reverse the columns
            for(int i=0; i<m; i++){
                int temp=a[i][c1];
                a[i][c1]=a[i][c2];
                a[i][c2]=temp;
            }
            c1++;
            c2--;
        }
    }
   
    public static void diagonalTraversal(int[][] a) {
        int n=a.length;

        for (int gap = 0; gap < n; gap++) {
            
            int i=0, j=gap;
            while(j < n){
                System.out.println(a[i][j]);
                i++;
                j++;
            }
        }
    }

    public static void saddlePoint(int[][] a) {
        int m = a.length;
        int n = a[0].length;

        for (int i = 0; i < m; i++) {
            
            int lic=0;  // least element index
            for (int c = 0; c < n; c++) {
                if(a[i][c] < a[i][lic]) lic=c;
            }

            boolean flag=true;
            for (int  r= 0; r < a.length; r++) {
               if(a[i][lic] < a[r][lic]){
                flag=false;
                break;
               }
                
            }

            if(flag){
                System.out.println(a[i][lic]);
                return;
            } 
        }

        System.out.println("Invalid input");
    }

    public static void searchInA2DSortedArray(int[][] a, int d) {
        int n=a.length;

        for(int i=n-1; i>=0; i--){
            
            if(d >= a[i][0]){
                for(int j=0; j<n; j++){
                   if(d==a[i][j]){
                       System.out.print(i+"\n"+j);
                       return;
                   }
                }
            } 
        }
        
        System.out.println("Not Found");
    }
 
    public static void ringRotate(int[][] a, int s, int r) {
        int[] oned = fill1DArrayFromShell(a, s);
        rotate1dArray(oned, r);
        fillShellFrom1DArray(a, s,oned);
    }

    public static int[] fill1DArrayFromShell(int[][] a, int s) {
        int m=a.length, n=a[0].length;

        int minr = s-1, minc = s-1, maxr = m-s, maxc = n-s;
        int arraySize = (maxr-minr + maxc-minc) * 2;
        
        int[] oned=new int[arraySize];
        int ce=0;

        

        for (int i = minr; i <= maxr; i++) { // left wall
            oned[ce]= a[i][minc];
            ce++;
        }
        minc++;

        for (int j = minc; j <= maxc; j++) { // bottom wall
            oned[ce]= a[maxr][j];
            ce++;
        }
        maxr--;

        for (int i = maxr; i >= minr; i--) { // right wall
            oned[ce]= a[i][maxc];
            ce++;
        }
        maxc--;

        for (int j = maxc; j >= minc; j--) { // top wall
            oned[ce]= a[minr][j];
            ce++;
        }
        minr++;
       


        return oned;
    }

    public static void fillShellFrom1DArray(int[][] a, int s, int[] oned) {
        int m=a.length, n=a[0].length;

        int minr = s-1, minc = s-1, maxr = m-s, maxc = n-s;
        
        int ce=0;

        for (int i = minr; i <= maxr; i++) { // left wall
            a[i][minc]= oned[ce];
            ce++;
        }
        minc++;

        for (int j = minc; j <= maxc; j++) { // bottom wall
            a[maxr][j]= oned[ce];
            ce++;
        }
        maxr--;

        for (int i = maxr; i >= minr ; i--) { // right wall
            a[i][maxc]= oned[ce];
            ce++;
        }
        maxc--;

        for (int j = maxc; j >= minc; j--) { // top wall
            a[minr][j]= oned[ce];
            ce++;
        }
        minr++;
       
}

    public static void rotate1dArray(int[] arr, int k) {
        k %= arr.length;
        if (k < 0)
            k += arr.length;

        reverseHelper(arr, 0, arr.length - k - 1);
        reverseHelper(arr, arr.length - k, arr.length - 1);
        reverseHelper(arr, 0, arr.length - 1);
    }

    public static void reverseHelper(int[] arr, int x, int y) {
        int i = x, j = y;

        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }
    }


    
}

