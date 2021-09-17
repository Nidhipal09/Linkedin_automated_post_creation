package BinarySearchTree;


public class BinarySearchTreeExplained {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    
    public static Node construct(int[] arr, int low, int high){
          if(low > high) return null;

          int mid = (low+high)/2;
          
          Node ln = construct(arr, 0, mid-1);
          Node rn = construct(arr, mid, arr.length-1); 

          Node node=new Node(arr[mid], ln, rn);
          return node;
    }
}
