package RecursionAndBacktracking;

public class Backtracking{
    public static void floodfill(int[][] maze, int sr, int sc, String asf,boolean[][] visited) {
        if(sr<0 || sc<0 || sr==maze.length || sc==maze[0].length || maze[sr][sc]==1 || visited[sr][sc]==true){
            return;
        }
        else if(sr==maze.length-1 && sc==maze[0].length-1){
            System.out.println(asf);
            return;
        }

        visited[sr][sc]=true;        
        floodfill(maze, sr-1, sc, asf+"t",visited);
        floodfill(maze, sr, sc-1, asf+"l",visited);
        floodfill(maze, sr+1, sc, asf+"d",visited);
        floodfill(maze, sr, sc+1, asf+"r",visited);
         visited[sr][sc]=false;
    }

    public static void printTargetSumSubsets(int[] arr, int i, String set, int sos, int tar) {
        
        if(i==arr.length){
            if(sos==tar)  System.out.println(set+".");
            return;
        }
        
        
        printTargetSumSubsets(arr, i+1, set+arr[i]+", ", sos+arr[i], tar);
        printTargetSumSubsets(arr, i+1, set, sos, tar);
    }


    public static void printNQueens(int[][] chess, String qsf, int r) {
        if(r==chess.length){
            System.out.println(qsf+".");
            return;
        }
        
        for(int c=0; c<chess.length; c++){
            if(isQueenSafe(chess,r,c)){
                chess[r][c]=1;
                printNQueens(chess, qsf+r+"-"+c+", ",r+1);
                chess[r][c]=0;
            }
        }
    }
    
    public static boolean isQueenSafe(int[][] chess, int r, int c){
        for(int row=r-1; row>=0; row--){
            if(chess[row][c]==1) return false;
        }
        
        for(int row=r-1, col=c-1; row>=0 && col>=0; row--, col--){
            if(chess[row][col]==1) return false;
        }
        
        for(int row=r-1, col=c+1; row>=0 && col<chess.length; row--, col++){
            if(chess[row][col]==1) return false;
        }
        
        return true;
    }

    public static void printKnightsTour(int[][] chess, int r, int c, int upcomingMove) {
     
        if(r<0 || c<0 || r>=chess.length || c>=chess.length || chess[r][c]!=0){
            return;
        }    
        else if(upcomingMove==chess.length*chess.length){
            chess[r][c]=upcomingMove;
            displayBoard(chess);
            chess[r][c]=0;
            return;
        }
        
        chess[r][c]=upcomingMove;
        printKnightsTour(chess, r-2, c+1, upcomingMove+1);
        printKnightsTour(chess, r-1, c+2, upcomingMove+1);
        printKnightsTour(chess, r+1, c+2, upcomingMove+1);
        printKnightsTour(chess, r+2, c+1, upcomingMove+1);
        printKnightsTour(chess, r+2, c-1, upcomingMove+1);
        printKnightsTour(chess, r+1, c-2, upcomingMove+1);
        printKnightsTour(chess, r-1, c-2, upcomingMove+1);
        printKnightsTour(chess, r-2, c-1, upcomingMove+1);
        chess[r][c]=0;
    }

    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}