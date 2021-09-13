package BinaryTree;

import java.util.Stack;

public class BinaryTreeExplained {

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

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void constructor(Integer[] arr) {
        Stack<Pair> st = new Stack<>();
        Node root = new Node(arr[0], null, null);
        st.push(new Pair(root, 1));
        int i = 1;

        while (st.size() > 0) {
            Pair p = st.peek();
            
            if (p.state == 1) {
                if (arr[i] != null){
                    p.node.left = new Node(arr[i], null, null);
                    Pair np = new Pair(p.node.left, 1);
                    st.push(np);
                }else p.node.left = null;
            i++;
            p.state++;
            } else if (p.state == 2) {
                if (arr[i] != null){
                    p.node.right = new Node(arr[i], null, null);
                    Pair np = new Pair(p.node.right, 1);
                    st.push(np);
                }else p.node.right = null;
            i++;
            p.state++;
            } else {
                st.pop();
            }
        }
    }

    public static void display(Node node) {
        String s = "";

        s += (node.left == null) ? " . " : node.left;
        s += " -> " + node.data + " <- ";
        s += (node.right == null) ? " . " : node.right;

        System.out.println(s);
        if (node.left != null)  display(node.left);
        if (node.right != null) display(node.right);
    }

    public static void main(String[] args) {
        Integer[] arr = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
                null, 50 };
        Node root = new Node(arr[0], null, null);

        constructor(arr);
        display(root);
       
    }


   

}
