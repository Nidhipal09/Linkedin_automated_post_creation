package BinaryTree;

import BinaryTree.BinaryTreeExplained.*;
import java.util.*;

public class BinaryTree {
    public static int size(Node node) {
        if (node == null)
            return 0;

        return size(node.left) + size(node.right) + 1;
    }

    public static int sum(Node node) {
        if (node == null)
            return 0;

        return sum(node.left) + sum(node.right) + node.data;
    }

    public static int max(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;
        int max = node.data;

        max = Math.max(max, Math.max(max(node.left), max(node.right)));

        return max;
    }

    public static int height(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;
        int h = -1;

        h = Math.max(h, Math.max(height(node.left), height(node.right)));

        return h + 1;
    }

    public static void traversal(Node node) {

        System.out.println(node.data + " in pre ");
        if (node.left != null)
            traversal(node.left);
        System.out.println(node.data + " in between ");
        if (node.right != null)
            traversal(node.right);
        System.out.println(node.data + " in post ");
    }

    public static void levelOrder(Node node) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);

        while (q.size() > 0) {
            int s = q.size();

            for (int i = 1; i <= s; i++) {
                Node rem = q.remove();
                System.out.print(rem.data + " ");
                if (rem.left != null)
                    q.add(rem.left);
                if (rem.right != null)
                    q.add(rem.right);
            }
            System.out.println();
        }
    }

    public static void iterativePrePostInTraversal(Node node) {

        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node, 1));

        String pre = "";
        String in = "";
        String post = "";

        while (st.size() > 0) {
            Pair p = st.peek();
            if (p.state == 1) {
                pre += p.node.data + "  ";
                p.state++;
                if (p.node.left != null)
                    st.push(new Pair(p.node.left, 1));

            } else if (p.state == 2) {
                in += p.node.data + "  ";
                p.state++;
                if (p.node.right != null)
                    st.push(new Pair(p.node.right, 1));

            } else {
                post += p.node.data + "  ";
                st.pop();
            }
        }

        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);

    }

    public static void printKLevelsDown(Node node, int k) {
        if (k == 0) {
            System.out.println(node.data);
            return;
        }
        if (node.left != null)
            printKLevelsDown(node.left, k - 1);
        if (node.right != null)
            printKLevelsDown(node.right, k - 1);
    }

    public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi) {
        if (node.left == null && node.right == null) {
            if (sum >= lo && sum <= hi)
                System.out.println(path);
            return;
        }

        if (node.left != null)
            pathToLeafFromRoot(node.left, path + " " + node.left.data, sum + node.left.data, lo, hi);
        if (node.right != null)
            pathToLeafFromRoot(node.right, path + " " + node.right.data, sum + node.right.data, lo, hi);
    }

    public static Node createLeftCloneTree(Node node) {
        if (node == null)
            return null;

        Node leftCloneTree = createLeftCloneTree(node.left);
        Node leftNode = new Node(node.data, leftCloneTree, null);
        node.left = leftNode;
        createLeftCloneTree(node.right);

        return node;
    }

    public static Node transBackFromLeftClonedTree(Node node) {
        if (node == null) {
            return null;
        }

        Node leftNode = transBackFromLeftClonedTree(node.left.left);
        node.left = leftNode;
        transBackFromLeftClonedTree(node.right);

        return node;
    }

    public static void printSingleChildNodes(Node node, Node parent) {
        if (node == null)
            return;

        if ((parent != null && parent.left == null && parent.right == node)
                || (parent != null && parent.left == node && parent.right == null)) {
            System.out.println(node.data);
            return;
        }

        printSingleChildNodes(node.left, node);
        printSingleChildNodes(node.right, node);
    }

    public static Node removeLeaves(Node node) {
        if (node == null)
            return null;
        if (node.left == null && node.right == null)
            return null;

        Node leftNewRoot = removeLeaves(node.left);
        Node rightNewRoot = removeLeaves(node.right);

        node.left = leftNewRoot;
        node.right = rightNewRoot;

        return node;
    }

    public static class DiaPair {
        int dia;
        int ht;
    }

    public static DiaPair diameter1(Node node) { // returns dia
        if (node == null) {
            DiaPair mdp = new DiaPair();
            mdp.ht = -1;
            mdp.dia = 0;
            return mdp;
        }

        DiaPair ldp = diameter1(node.left); // returns max distance btw two nodes on LHS
        DiaPair rdp = diameter1(node.right); // returns max distance btw two nodes on RHS

        DiaPair mdp = new DiaPair();
        mdp.ht = Math.max(ldp.ht, rdp.ht) + 1;

        mdp.dia = Math.max(ldp.ht + rdp.ht + 2, Math.max(ldp.dia, rdp.dia));

        return mdp;
    }

    static int tilt = 0;

    public static int tilt(Node node) { // return sum and changes tilt
        if (node == null)
            return 0;

        int ls = tilt(node.left);
        int rs = tilt(node.right);
        tilt += Math.abs(ls - rs);

        return node.data + ls + rs;
    }

    

}