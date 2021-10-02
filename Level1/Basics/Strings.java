package Basics;
import java.util.*;

public class Strings{
    public static boolean isPalindrome(String str){
        int i=0;
        int j=str.length()-1;
        
        while(i<=j){
           if(str.charAt(i)!=str.charAt(j)) return false;   
           i++;
           j--;
        } 
        return true;
     }
 
     public static void palindromicSS(String str){
       int n=str.length();		
       for(int i=0; i<= n-1; i++){
          for(int j=i+1; j<= n; j++){
              String ss=str.substring(i,j);
              if(isPalindrome(ss))
              System.out.println(ss);
          } 
       }	
     }

     public static String compression1(String str){
		String ans = ""+str.charAt(0);

        for(int i=1; i<str.length(); i++){
            char ch=str.charAt(i);
            char ansprev = ans.charAt(ans.length()-1);
            if(ch!=ansprev) ans += ch;
        }

		return ans;
	}

	 public static String compression2(String str){
		String ans = ""+str.charAt(0);

        int count=1;
        
        for(int i=1; i<str.length(); i++){
            char ch=str.charAt(i);
            char ansprev = ans.charAt(ans.length()-1);

            if(ch!=ansprev) {
                if(count==1) ans += ch;
                else{ 
                    ans += count+""+ch+"";
                    count=1;
                }
            }
            else count++;
        }
        
        
        if(count!=1){ 
            ans += count;
        }

        return ans;
	}
	
    public static String toggleCase(String str){
		StringBuilder sb=new StringBuilder(str);

        for(int i=0; i<sb.length(); i++){
            char ch = sb.charAt(i);
             
            if(ch>= 'a' && ch<= 'z'){
                char uch= (char)(ch-'a'+'A');
                sb.setCharAt(i, uch);
            }
            else if(ch>= 'A' && ch<= 'Z'){
                char lch= (char)(ch-'A'+'a');
                sb.setCharAt(i, lch);
            }
        }
        
        return sb.toString();
	}

    public static String asciiDifference(String str){
		StringBuilder sb=new StringBuilder();
		sb.append(str.charAt(0));

        int i=1;
        while(i< str.length()){
            char ch = str.charAt(i);
            char prev = str.charAt(i-1);
            
            int n= ch-prev;
            sb.append(n+""+ch);
            
            i++;
        }
        
        return sb.toString();
	}
	

    public static void permutationsOfAString(String s){
		int l=s.length();
        int n=factorial(l);

		for(int i=0; i<n; i++){

            int temp=i, div=l;
            StringBuilder sb=new StringBuilder(s); 
            while(div>0){
                int p=temp%div;
                temp /= div;
                div--;
                System.out.print(sb.charAt(p));
                sb.deleteCharAt(p);
                
            }
            System.out.println();
        }
	}

    public static int factorial(int n){
        if(n==1) return 1;
        return n * factorial(n-1);
    }

    public static void solution(ArrayList<Integer> al){
		
        for(int i=al.size()-1; i>=0; i--){
            int e=al.get(i);
            if(isPrime(e)) al.remove(i);
        }
	}

    public static boolean isPrime(int n) {
        for(int div=2; div*div <= n; div++){
            if(n%div == 0){
               return false;
            }
        }
        return true;
    }
}
