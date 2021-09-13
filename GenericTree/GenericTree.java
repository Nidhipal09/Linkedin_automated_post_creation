package GenericTree;

import GenericTree.GenericTreeExplained.*;
import java.util.*;

public class GenericTree {

    public static int size(Node node) {
        int s = 0;

        for (Node child : node.children) {
            s += size(child);
        }
        s += 1;

        return s;
    }

    public static int max(Node node) {
        int m = Integer.MIN_VALUE;

        for (Node child : node.children) {
            int cm = max(child);
            m = Math.max(m, cm);
        }
        m = Math.max(m, node.data);

        return m;
    }

    public static int height(Node node) {
        int h = -1;

        for (Node child : node.children) {
            h = Math.max(h, height(child));
        }

        return h + 1;
    }

    public static void traversals(Node node) {

        System.out.println("Node Pre " + node.data);

        for (Node child : node.children) {
            System.out.println("Edge Pre " + node.data + "--" + child.data);
            traversals(child);
            System.out.println("Edge Post " + node.data + "--" + child.data);
        }

        System.out.println("Node Post " + node.data);
    }

    public static void levelOrder(Node node) {

        Queue<Node> q = new ArrayDeque<>();
        q.add(node);

        while (q.size() != 0) {
            Node rem = q.remove();
            System.out.print(rem.data + " ");

            for (Node child : rem.children) {
                q.add(child);
            }
        }

        System.out.print(".");
    }

    public static void levelOrderLinewise(Node node) {
        Queue<Node> mq = new ArrayDeque<>();
        Queue<Node> cq = new ArrayDeque<>();
        mq.add(node);

        while (mq.size() != 0) {
            Node rem = mq.remove();
            System.out.print(rem.data + " ");

            for (Node child : rem.children) {
                cq.add(child);
            }

            if (mq.size() == 0) {
                mq = cq;
                cq = new ArrayDeque<>();
                System.out.println();
            }

        }

    }

    public static void levelOrderLinewiseZZ(Node node) {
        Stack<Node> mst = new Stack<>();
        Stack<Node> cst = new Stack<>();
        mst.push(node);
        int level = 1;

        while (mst.size() != 0) {
            Node rem = mst.pop();
            System.out.print(rem.data + " ");

            if (level % 2 == 0) {
                for (int i = rem.children.size() - 1; i >= 0; i--) {
                    cst.push(rem.children.get(i));
                }
            } else {
                for (int i = 0; i < rem.children.size(); i++) {
                    cst.push(rem.children.get(i));
                }
            }

            if (mst.size() == 0) {
                mst = cst;
                cst = new Stack<>();
                System.out.println();
                level++;
            }
        }
    }

    public static void mirror(Node node) {
        for (Node child : node.children) {
            mirror(child);
        }

        Collections.reverse(node.children);
    }

    public static void removeLeaves(Node node) {
        for (int i = node.children.size() - 1; i >= 0; i--) {
            Node child = node.children.get(i);
            if (child.children.size() == 0)
                node.children.remove(child);
        }

        for (Node child : node.children) {
            removeLeaves(child);
        }
    }

    public static Node linearize(Node node) {
        if (node.children.size() == 0)
            return node;

        Node lt = linearize(node.children.get(node.children.size() - 1));
        while (node.children.size() > 1) {
            Node last = node.children.remove(node.children.size() - 1);
            Node slast = node.children.get(node.children.size() - 1);
            Node slt = linearize(slast);
            slt.children.add(last);
        }
        return lt;
    }

    public static boolean find(Node node, int data) {
        if (node.data == data)
            return true;

        for (Node child : node.children) {
            if (find(child, data))
                return true;
        }

        return false;
    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int data) {

        if (node.data == data) {
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(node.data);
            return ans;
        }

        for (Node child : node.children) {
            ArrayList<Integer> path = nodeToRootPath(child, data);
            if (path.size() > 0) {
                path.add(node.data);
                return path;
            }
        }

        return new ArrayList<>();
    }

    public static int lca(Node node, int d1, int d2) {
        ArrayList<Integer> pathd1 = nodeToRootPath(node, d1);
        ArrayList<Integer> pathd2 = nodeToRootPath(node, d2);

        int i = pathd1.size() - 1;
        int j = pathd2.size() - 1;

        while (i >= 0 && j >= 0) {
            if (pathd1.get(i) != pathd2.get(j))
                break;
            i--;
            j--;
        }

        return pathd1.get(i + 1);

    }

    public static int distanceBetweenNodes(Node node, int d1, int d2) {
        ArrayList<Integer> p1 = nodeToRootPath(node, d1);
        ArrayList<Integer> p2 = nodeToRootPath(node, d2);

        int i = p1.size() - 1;
        int j = p2.size() - 1;

        while (i >= 0 && j >= 0 && p1.get(i) == p2.get(j)) {
            i--;
            j--;
        }

        i++;
        j++;

        return i + j;
    }

    public static boolean areSimilar(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size())
            return false;

        for (int i = 0; i < n1.children.size(); i++) {
            if (!areSimilar(n1.children.get(i), n2.children.get(i)))
                return false;
        }

        return true;
    }

    public static boolean areMirror(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size())
            return false;

        int s = n1.children.size() - 1;
        for (int i = 0; i < s; i++) {
            if (!areMirror(n1.children.get(i), n2.children.get(s - i)))
                return false;
        }

        return true;
    }

    public static boolean IsSymmetric(Node node) {
        return areMirror(node, node);
    }

    static Node predecessor;
    static Node successor;
    static int state = 0;

    public static void predecessorAndSuccessor(Node node, int data) {
        if (state == 0) {
            if (node.data == data)
                state = 1;
            else
                predecessor = node;
        } else if (state == 1) {
            successor = node;
            state = 2;
        } else {
            return;
        }
        for (Node child : node.children) {
            predecessorAndSuccessor(child, data);
        }
    }

    static int ceil;
    static int floor;

    public static void ceilAndFloor(Node node, int data) {
        if (node.data > data && node.data < ceil)
            ceil = node.data;
        else if (node.data < data && node.data > floor)
            floor = node.data;

        for (Node child : node.children) {
            ceilAndFloor(child, data);
        }
    }

    public static int kthLargest(Node node, int k) {
        int maxValue = Integer.MAX_VALUE;
        floor = Integer.MIN_VALUE;

        for (int i = 0; i < k; i++) {
            ceilAndFloor(node, maxValue); // floor of max value will be the first largest
            maxValue = floor;
            floor = Integer.MIN_VALUE;
        }

        return maxValue;
    }

    static int msn; // max sum node
    static int ms; // max sum

    public static int maxSubtreeSum(Node node) { // return sum of subtree

        int sum = node.data;
        for (Node child : node.children) {
            sum += maxSubtreeSum(child);
        }
        if (sum > ms) {
            ms = sum;
            msn = node.data;
        }

        return sum;
    }

    static int dia = 0;

    public static int diaReturnsHeight(Node node) { // returns height
        int dch = -1;
        int sdch = -1;

        for (Node child : node.children) {
            int ch = diaReturnsHeight(child);
            if (ch > dch) {
                sdch = dch;
                dch = ch;
            } else if (ch > sdch) {
                sdch = ch;
            }
        }

        if ((dch + sdch + 2) > dia)
            dia = dch + sdch + 2;

        return dch + 1;
    }

    private static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    static String pre = "";
    static String post = "";

    public static void IterativePreandPostOrder(Node node) {
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node, -1));

        while (st.size() > 0) {
            Pair p = st.peek();
            if (p.state == -1) {
                pre += p.node.data + " ";
                p.state = 0;
            } else if (p.state == p.node.children.size()) {
                post += p.node.data + " ";
                st.pop();
            } else {
                Node child = p.node.children.get(p.state);
                st.push(new Pair(child, -1));
                p.state++;
            }
        }
    }

    
}
