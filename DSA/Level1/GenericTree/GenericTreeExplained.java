package GenericTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

public class GenericTreeExplained {
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node() {

        }

        Node(int data) {
            this.data = data;
        }
    }

    // display(10) -> self(10) + display(20) + display(30) + display(40)
    public static void displayNode(Node node) {

        System.out.print(node.data + " -> ");
        for (Node child : node.children) {
            System.out.print(child.data + ", ");
        }
        System.out.println(".");

        for (Node child : node.children) {
            displayNode(child);
        }
    }

    public static class GenericTree implements Iterable<Integer> {
        Node root;

        GenericTree(Node root) {
            this.root = root;
        }

        @Override
        public Iterator<Integer> iterator() {
            Iterator<Integer> obj = new GtPreIterator(root);
            return obj;
        }
    }

    public class GtPreIterator implements Iterator<Integer> {
        Integer nval;
        Stack<Pair> st;

        public GtPreIterator(Node root) {
            st= new Stack<>();
            st.push(new Pair(root, -1));
            next();
        }

        @Override
        public boolean hasNext() {
            if (nval == null)
                return false;
            else
                return true;
        }

        @Override
        public Integer next() {
            Integer fr=nval;
            nval=null;

            while (st.size() > 0) {
                Pair p = st.peek();
                if (p.state == -1) {
                    nval=p.node.data;
                    p.state = 0;
                    break;
                } else if (p.state == p.node.children.size()) {
                    st.pop();
                } else {
                    Node child = p.node.children.get(p.state);
                    st.push(new Pair(child, -1));
                    p.state++;
                }
            }

            return fr;
        }
    }

    private static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 40, -1, 50, -1, -1, 30, 80, -1, 100, -1, -1, 40, 60, -1, -1, -1 };

        Stack<Node> st = new Stack<>();
        Node root = null;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                st.pop();
            } else {
                Node node = new Node();
                node.data = arr[i];
                if (st.size() == 0) {
                    root = node;
                } else {
                    st.peek().children.add(node);
                }
                st.push(node);
            }
        }

        // displayNode(root);
        // moreTraversal3(root);
        // multisolver(root, 0);
        // System.out.println(size + "  " + height + "  " + max + "  " + min);

        GenericTree gt = new GenericTree(root);
        for (int val : gt) { // Syntactical sugar dependent upon iterable
             System.out.println(val);
        }

        Iterator<Integer> gti = gt.iterator();
        while (gti.hasNext()) {
            System.out.println(gti.next());
        }
    }

    public static void moreTraversal2(Node node) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        q.add(new Node(-1));

        while (q.size() > 0) {
            Node rem = q.remove();
            if (rem.data != -1) {
                System.out.print(rem.data + " ");
                for (Node child : rem.children) {
                    q.add(child);
                }
            } else {
                if (q.size() > 0) {
                    q.add(new Node(-1));
                    System.out.println();
                }
            }
        }
    }

    public static void moreTraversal3(Node node) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);

        while (q.size() > 0) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                Node rem = q.remove();
                System.out.print(rem.data + " ");

                for (Node child : rem.children) {
                    q.add(child);
                }
            }
            System.out.println();
        }
    }

    

    public static void moreTraversal4(Node node) {
        Queue<Pair> q = new ArrayDeque<>();
        int level = 1;
        q.add(new Pair(node, level));

        while (q.size() > 0) {
            Pair rem = q.remove();

            if (rem.level > level) {
                level = rem.level;
                System.out.println();
            }

            System.out.print(rem.node.data + " ");
            for (Node child : rem.node.children) {
                q.add(new Pair(child, level + 1));
            }
        }
    }

    public static Node linearize1(Node node) {
        if (node.children.size() == 0)
            return node;

        Node lt = linearize1(node.children.get(node.children.size() - 1));
        while (node.children.size() > 1) {
            Node last = node.children.remove(node.children.size() - 1);

            Node slast = node.children.get(node.children.size() - 1);
            Node slt = linearize1(slast);
            slt.children.add(last);
        }
        return lt;
    }

    static int size = 0, height = -1, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    public static void multisolver(Node node, int depth) {

        size++;
        height = Math.max(height, depth);
        max = Math.max(max, node.data);
        min = Math.min(min, node.data);
        for (Node child : node.children) {
            multisolver(child, depth + 1);
        }

    }

}
