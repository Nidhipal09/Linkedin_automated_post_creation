package RecursionAndBacktracking;

public class RecursionOnTheWayUp{
    public static void printSS(String str, String ans) {
        if(str.length()==0){
            System.out.println(ans);    
            return;
        }
        
        char ch=str.charAt(0);
        String ros=str.substring(1);
        
        printSS(ros,ans+ch);
        printSS(ros,ans);
    }

    static String[] map = {".;","abc","def","ghi","jkl","mno","pqrs","tu", "vwx", "yz"};
    
    public static void printKPC(String s, String asf) {
        if(s.length()==0){
            System.out.println(asf);
            return;
        }
        
        char ch=s.charAt(0);
        String ros=s.substring(1);
        String mapped=map[ch-'0'];
        for(int i=0; i<mapped.length(); i++){
            char mc=mapped.charAt(i);
            printKPC(ros,asf+mc);
        }
    }


    public static void printStairPaths(int n, String path) {
        if(n==0){
            System.out.println(path);
            return;
        }else if(n<0) return; 
        
        printStairPaths(n-1,path+"1");
        printStairPaths(n-2,path+"2");
        printStairPaths(n-3,path+"3");
    }

    public static void printMazePaths(int sr, int sc, int dr, int dc, String psf) {
        if(sr==dr && sc==dc){
        System.out.println(psf);
         return;
        }else if(sr>dr || sc>dc) return; 
         
        printMazePaths(sr,sc+1,dr,dc,psf+"h");
        printMazePaths(sr+1,sc,dr,dc,psf+"v");
     }

     public static void printMazePathsWithJumps(int sr, int sc, int dr, int dc, String psf) {
        if(sr==dr && sc==dc){
         System.out.println(psf);
         return;
        }
        
        for(int j=1; j<= dc-sc; j++){
         printMazePaths(sr,sc+j,dr,dc,psf+"h"+j);            } 
         
          for(int i=1; i<= dr-sr; i++){
        printMazePaths(sr+i,sc,dr,dc,psf+"v"+i);
        }
        
        for(int d=1; d<= dr-sr && d<=dc-sc; d++){
        printMazePaths(sr+d,sc+d,dr,dc,psf+"d"+d);
        }
     }

     public static void printPermutations(String s, String asf) {
        if(s.length()==0){
            System.out.println(asf);
            return;
        }
        
        for(int i=0; i<s.length(); i++){
            String pres=s.substring(0,i);
            char ch=s.charAt(i);
            String posts=s.substring(i+1);
            printPermutations(pres+posts,asf+ch);
        }
    }

    

    public static void printEncodings(String s, String asf) {
        
        if(s.length()==0){
            System.out.println(asf);
            return;
        }
        
        
        char ch=s.charAt(0);
        if(ch == '0'){
            return;
        }else{
            int chn= ch - '0';
            char code= (char)('a'+chn-1);
            String ros=s.substring(1);
            printEncodings(ros,asf+code);
            
        }
            
        if(s.length()>=2){
            String ftc=s.substring(0,2);
            int ftn=Integer.parseInt(ftc);
            if(ftn <= 26){
                char code= (char)('a'+ftn-1);
                String ros=s.substring(2);
                printEncodings(ros, asf+code);
            }
        }
    }



    
}