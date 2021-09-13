package String;

public class StringAndStringBuilder{
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

	public static void solution(String str){
      int n=str.length();		
	  for(int i=0; i<= n-1; i++){
	     for(int j=i+1; j<= n; j++){
	         String ss=str.substring(i,j);
	         if(isPalindrome(ss))
	         System.out.println(ss);
	     } 
	  }	
	}
}