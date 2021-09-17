package RecursionAndBacktracking;
import java.util.ArrayList;

public class RecursionWithArralist{
     public static ArrayList<String> gss(String s) {
        if(s.length()==1){
          ArrayList<String> bcal=new ArrayList<>();
          bcal.add("");
          bcal.add(s);
          return bcal;
        }
        
        char ch=s.charAt(0);
        ArrayList<String> ral=gss(s.substring(1));
         
        ArrayList<String> mal=new ArrayList<>();
        for(String val: ral){
            mal.add(val);
        }
        
        for(String val: ral){
            mal.add(ch+val);
        }
        
        return mal;
    }

    static String[] map = {".;","abc","def","ghi","jkl","mno"       ,"pqrs","tu", "vwx", "yz"};
      
    public static ArrayList<String> getKPC(String s) {
        if(s.length()==0){
            ArrayList<String> bcal=new ArrayList<>();
            bcal.add("");
            return bcal;
        }
        
        char ch=s.charAt(0);
        
        ArrayList<String> ral=getKPC(s.substring(1));
        
        ArrayList<String> mal=new ArrayList<>();
        
        for(char cmap: map[ch-'0'].toCharArray()){
           
            for(String val:ral){
                mal.add(cmap+val);
            }
        }
        
        return mal;
    }

    public static ArrayList<String> getStairPaths(int n) {
        if(n==0){
            ArrayList<String> bcal=new ArrayList<>();
            bcal.add("");
            return bcal;   
        }else if(n<0){
            return new ArrayList<>();
        } 
       
       ArrayList<String> j1paths= getStairPaths(n-1);
       ArrayList<String> j2paths= getStairPaths(n-2);
       ArrayList<String> j3paths= getStairPaths(n-3);
       
        ArrayList<String> ans = new ArrayList<>();
        
       for(String path: j1paths) ans.add("1"+path);
       for(String path: j2paths) ans.add("2"+path);
       for(String path: j3paths) ans.add("3"+path);
       
       return ans;
    }

    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
        if(sr==dr && sc==dc){
            ArrayList<String> bcal= new ArrayList<>();
            bcal.add("");
            return bcal;
        }else if(sr>dr || sc>dc){
            return new ArrayList<>();
        }     
        
        ArrayList<String> hpaths = getMazePaths(sr,sc+1,dr,dc);
        ArrayList<String> vpaths = getMazePaths(sr+1,sc,dr,dc);
        
        ArrayList<String> ans= new ArrayList<>();
        for(String path: hpaths) ans.add("h"+path);
        for(String path: vpaths) ans.add("v"+path);
        return ans;
     }

     public static ArrayList<String> getMazePathsWithJumps(int sr, int sc, int dr, int dc) {
        if(sr==dr && sc==dc){
            ArrayList<String> bcal= new ArrayList<>();
            bcal.add("");
            return bcal;
        }else if(sr>dr || sc>dc){
            return new ArrayList<>();
        }     
        
        ArrayList<String> ans= new ArrayList<>();
        
        for(int j=1; j<= dc-sc; j++){
          ArrayList<String> hpaths = getMazePaths(sr,sc+j,dr,dc);   
          for(String path: hpaths) ans.add("h"+j+path);
        }
       
        for(int i=1; i<=dr-sr; i++){
          ArrayList<String> vpaths = getMazePaths(sr+i,sc,dr,dc);   
          for(String path: vpaths) ans.add("v"+i+path);
        }
        
        for(int i=1; i<=dr-sr && i<=dc-sc; i++){
          ArrayList<String> dpaths = getMazePaths(sr+i,sc+i,dr,dc);   
          for(String path: dpaths) ans.add("d"+i+path);
        }
        
        
        return ans;
     }
}