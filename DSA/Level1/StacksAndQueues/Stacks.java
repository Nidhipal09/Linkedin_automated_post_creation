package StacksAndQueues;

import java.util.*;

public class Stacks {
    public static boolean duplicateBrackets(String s) {

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == ')') {
                if (st.peek() == '(')
                    return true;
                else {
                    while (st.peek() != '(') {
                        st.pop();
                    }
                    st.pop();
                }
            } else {
                st.push(ch);
            }
        }

        return false;
    }

    public static boolean balancedBrackets(String s) {

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else if (st.size() == 0 && (ch == ')' || ch == '}' || ch == ']')) {
                st.push(ch);
            } else if (ch == ')') {
                if (st.peek() != '(')
                    return false;
                else
                    st.pop();
            } else if (ch == ']') {
                if (st.peek() != '[')
                    return false;
                else
                    st.pop();
            } else if (ch == '}') {
                if (st.peek() != '{')
                    return false;
                else
                    st.pop();
            }

        }

        if (st.size() > 0)
            return false;
        return true;
    }

    public static int[] nextGreaterElementToRight(int[] arr) {
        int n = arr.length;

        int[] ans = new int[n];
        ans[n - 1] = -1;
        Stack<Integer> st = new Stack<>();
        st.push(arr[n - 1]);

        for (int i = n - 2; i >= 0; i--) {

            while (st.size() > 0 && arr[i] > st.peek()) { // pop the smaller elements
                st.pop();
            }
            if (st.size() == 0) // check the size
                ans[i] = -1;
            else
                ans[i] = st.peek(); // work

            st.push(arr[i]); // push the current element ->pop assign push
        }

        return ans;
    }

    public static int[] stockSpan(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        ans[0] = 1;
        Stack<Integer> st = new Stack<>();
        st.push(0);

        for (int i = 1; i < n; i++) {
            while (st.size() > 0 && arr[i] > arr[st.peek()]) {
                st.pop();
            }
            if (st.size() == 0)
                ans[i] = i + 1;
            else
                ans[i] = i - st.peek();

            st.push(i);
        }

        return ans;
    }

    public static int largestAreaHistogram(int[] a) {
        int n = a.length;
        int maxArea = 0;

        int[] nser = new int[n]; // next smaller element on right
        int[] nsel = new int[n]; // next smaller element on left

        nser[n - 1] = n;
        Stack<Integer> st = new Stack<>();
        st.push(n - 1);

        for (int i = n - 2; i >= 0; i--) {
            while (st.size() > 0 && a[i] <= a[st.peek()]) {
                st.pop();
            }
            if (st.size() == 0)
                nser[i] = n;
            else
                nser[i] = st.peek();

            st.push(i);
        }

        while (st.size() > 0)
            st.pop();

        nsel[0] = -1;
        st.push(0);

        for (int i = 1; i < n; i++) {
            while (st.size() > 0 && a[i] <= a[st.peek()]) {
                st.pop();
            }
            if (st.size() == 0)
                nsel[i] = -1;
            else
                nsel[i] = st.peek();

            st.push(i);
        }

        for (int i = 0; i < n; i++) {
            int width = nser[i] - nsel[i] - 1;
            int height = a[i];
            int area = width * height;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public static void slidingWindowMax(int[] a, int k) {
        int n = a.length;

        int[] ngre = new int[n];
        ngre[n - 1] = n;
        Stack<Integer> st = new Stack<>();
        st.push(n - 1);

        for (int i = n - 2; i >= 0; i--) {

            while (st.size() > 0 && a[i] >= a[st.peek()]) { // pop the smaller elements
                st.pop();
            }
            if (st.size() == 0) // check the size
                ngre[i] = n;
            else
                ngre[i] = st.peek(); // work

            st.push(i); // push the current element ->pop assign push
        }

        int[] ans = new int[n - k];
        int j = 0;
        for (int i = 0; i <= n - k; i++) {
            j = i; // next greater element index

            while (ngre[j] <= i + k - 1) {
                j = ngre[j];
            }

            System.out.println(a[j]);

        }
    }

    public static void infixEval(String s) {

        Stack<Character> opt = new Stack<>();
        Stack<Integer> opn = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(')
                opt.push(ch);
            else if (ch == ')') {
                while (opt.peek() != '(') {
                    int b = opn.pop();
                    int a = opn.pop();

                    char popopt = opt.pop();

                    int val = operation(a, b, popopt);
                    opn.push(val);
                }

                opt.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (opt.size() > 0 && opt.peek() != '(' && precendence(ch) <= precendence(opt.peek())) {
                    int b = opn.pop();
                    int a = opn.pop();

                    char popopt = opt.pop();

                    int val = operation(a, b, popopt);
                    opn.push(val);
                }

                opt.push(ch);
            } else if (Character.isDigit(ch)) {
                opn.push(ch - '0');
            }
        }

        while (opt.size() > 0) {
            int b = opn.pop();
            int a = opn.pop();

            char popopt = opt.pop();

            int val = operation(a, b, popopt);
            opn.push(val);
        }

        System.out.println(opn.pop());
    }

    public static int precendence(char opt) {
        if (opt == '+')
            return 1;
        else if (opt == '-')
            return 1;
        else if (opt == '*')
            return 2;
        else
            return 2;
    }

    public static int operation(int a, int b, char opt) {
        if (opt == '+')
            return a + b;
        else if (opt == '-')
            return a - b;
        else if (opt == '*')
            return a * b;
        else
            return a / b;
    }

    public static void infixConv(String s) {

        Stack<String> pre = new Stack<>();
        Stack<String> post = new Stack<>();
        Stack<Character> opt = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(')
                opt.push(ch);
            else if (ch == ')') {
                while (opt.peek() != '(') {
                    String bpre = pre.pop();
                    String apre = pre.pop();

                    char popopt = opt.pop();

                    String val = popopt + apre + bpre;
                    pre.push(val);

                    String bpost = post.pop();
                    String apost = post.pop();

                    String val1 = apost + bpost + popopt;
                    post.push(val1);
                }

                opt.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (opt.size() > 0 && opt.peek() != '(' && precendence(ch) <= precendence(opt.peek())) {
                    String bpre = pre.pop();
                    String apre = pre.pop();

                    char popopt = opt.pop();

                    String val = popopt + apre + bpre;
                    pre.push(val);

                    String bpost = post.pop();
                    String apost = post.pop();

                    String val1 = apost + bpost + popopt;
                    post.push(val1);
                }

                opt.push(ch);
            } else {
                pre.push(ch + "");
                post.push(ch + "");
            }
        }

        while (opt.size() > 0) {
            String bpre = pre.pop();
            String apre = pre.pop();

            char popopt = opt.pop();

            String val = popopt + apre + bpre;
            pre.push(val);

            String bpost = post.pop();
            String apost = post.pop();

            String val1 = apost + bpost + popopt;
            post.push(val1);
        }

        System.out.println(pre.pop());
        System.out.println(post.pop());
    }

    public static void postEvalAndConv(String s) {

        Stack<String> pre = new Stack<>();
        Stack<String> in = new Stack<>();
        Stack<Integer> val = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                int bval = val.pop();
                int aval = val.pop();

                int xval = operation(aval, bval, ch);
                val.push(xval);

                String bpre = pre.pop();
                String apre = pre.pop();

                String xpre = ch + apre + bpre;
                pre.push(xpre);

                String bin = in.pop();
                String ain = in.pop();

                String xin = "(" + ain + ch + bin + ")";
                in.push(xin);

            } else {
                pre.push(ch + "");
                in.push(ch + "");
                val.push(ch - '0');
            }
        }

        System.out.println(val.pop());
        System.out.println(in.pop());
        System.out.println(pre.pop());
    }

    public static void preEvalAndConv(String s) {

        Stack<String> post = new Stack<>();
        Stack<String> in = new Stack<>();
        Stack<Integer> val = new Stack<>();

        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                int aval = val.pop();
                int bval = val.pop();

                int xval = operation(aval, bval, ch);
                val.push(xval);

                String apost = post.pop();
                String bpost = post.pop();

                String xpost = apost + bpost + ch;
                post.push(xpost);

                String ain = in.pop();
                String bin = in.pop();

                String xin = "(" + ain + ch + bin + ")";
                in.push(xin);

            } else {
                post.push(ch + "");
                in.push(ch + "");
                val.push(ch - '0');
            }
        }

        System.out.println(val.pop());
        System.out.println(in.pop());
        System.out.println(post.pop());
    }

    public static void findCelebrity(int[][] arr) {
        int n = arr.length - 1;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i <= n; i++) {
            st.push(i);
        }

        while (st.size() >= 2) {
            int a = st.pop();
            int b = st.pop();

            if (arr[a][b] == 1)
                st.push(b); // elimination of non-celebrities
            else
                st.push(a);
        }

        int x = st.pop(); // potential celebrity

        for (int i = 0; i <= n; i++) {
            if (i != x && arr[i][x] == 0) {
                System.out.println("none");
                return;
            } else if (arr[x][i] == 1) {
                System.out.println("none");
                return;
            }
        }

        System.out.println(x);

    }

    public static void margeOverlappingIntervals(int[][] a) {
        Pair[] pairs = new Pair[a.length];
        for (int i = 0; i < a.length; i++) {
            pairs[i] = new Pair(a[i][0], a[i][1]);
        }

        Arrays.sort(pairs);

        Stack<Pair> st = new Stack<>();

        st.push(pairs[0]);

        for (int i = 1; i < a.length; i++) {
            int ast = pairs[i].st;
            int aet = pairs[i].et;

            if (ast <= st.peek().et) {
                Pair s = st.pop();
                Pair p = new Pair(Math.min(s.st, ast), Math.max(s.et, aet));
                st.push(p);
            } else {
                Pair p = new Pair(ast, aet);
                st.push(p);
            }
        }

        Stack<Pair> newst = new Stack<>();
        while (st.size() > 0) {
            newst.push(st.pop());
        }

        while (newst.size() > 0) {
            Pair p = newst.pop();
            System.out.println(p.st + " " + p.et);
        }
    }

    public static class Pair implements Comparable<Pair> {
        int st;
        int et;

        Pair(int st, int et) {
            this.st = st;
            this.et = et;
        }

        public int compareTo(Pair p) {
            return this.et - p.et;
        }
    }

    public static void smallestNumFollowingPattern(String s) {
        int n = s.length();
        int count = 1;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            st.push(count);
            count++;
            if (ch == 'i') {

                while (st.size() > 0) {
                    System.out.print(st.pop());
                }
            }

        }

        st.push(count);
        while (st.size() > 0) {
            System.out.print(st.pop());
        }
    }

    public static class CustomStack {
        int[] data;
        int tos;

        public CustomStack(int cap) {
            data = new int[cap];
            tos = -1; // top of the stack
        }

        int size() {
            return tos + 1;
        }

        void display() {
            for (int i = tos; i >= 0; i--) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        void push(int val) {
            if (tos == data.length - 1) {
                System.out.println("Stack overflow");
                return;
            }
            tos++;
            data[tos] = val;
        }

        void pushDynamic(int val) {

            if (tos == data.length - 1) {
                int[] newData = new int[2 * data.length];
                for (int i = 0; i <= tos; i++) {
                    newData[i] = data[i];
                }
                data = newData;
            }
            tos++;
            data[tos] = val;

        }

        int pop() {
            if (tos == -1) {
                System.out.println("Stack underflow");
                return -1;
            }
            int temp = data[tos];
            tos--;
            return temp;
        }

        int top() {
            if (tos == -1) {
                System.out.println("Stack underflow");
                return -1;
            }
            return data[tos];
        }
    }

    public static class MinStack {
        Stack<Integer> allData;
        Stack<Integer> minData;

        public MinStack() {
            allData = new Stack<>();
            minData = new Stack<>();
        }

        int size() {
            return allData.size();
        }

        void push(int val) {
            allData.push(val);
            if (minData.size() == 0 || minData.peek() >= val)
                minData.push(val);

        }

        int pop() {
            int n = allData.pop();
            if (n == minData.peek())
                minData.pop();
            return n;
        }

        int top() {
            return allData.peek();
        }

        int min() {
            return minData.peek();
        }
    }

    public static class MinStack1 {
        Stack<Integer> data;
        int min;

        public MinStack1() {
            data = new Stack<>();
        }

        int size() {
            return data.size();
        }

        void push(int val) {
            if (data.size() == 0)
                min = val;

            if (val >= min)
                data.push(val);
            else {
                data.push(val + val - min); // storing a fake smaller value than min
                min = val; // original value stored in min
            }
        }

        int pop() {
            if (data.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            if (data.peek() < min) {
                int temp = min;
                min = 2 * min - data.pop();
                return temp;
            } else {
                return data.pop();
            }
        }

        int top() {
            if (data.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            if (data.peek() >= min) {
                return data.peek();
            } else {
                return min;
            }
        }

        int min() {
            if (data.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            return min;
        }
    }

    public static class CustomQueue {
        int[] data;
        int front;
        int size;

        public CustomQueue(int cap) {
            data = new int[cap];
            front = 0;
            size = 0;
        }

        int size() {
            return size;
        }

        void display() {
            for (int i = 0; i < size; i++) {
                int idx = (front + i) % data.length;
                System.out.print(data[idx] + " ");
            }
            System.out.println();
        }

        void add(int val) {
            if (size == data.length) {
                System.out.println("Queue overflow");
                return;
            }
            int rear = (front + size) % data.length;
            data[rear] = val;
            size++;
        }

        void add1(int val) {
            // write ur code here
            if (size == data.length) {
                int[] newData = new int[2 * data.length];
                for (int i = 0; i < size; i++) {
                    int idx = (front + i) % data.length;
                    newData[i] = data[idx];
                }
                data = newData;
                front = 0;

                int idx = (front + size) % data.length;
                data[idx] = val;
                size++;

            } else {
                int idx = (front + size) % data.length;
                data[idx] = val;
                size++;
            }
        }

        int remove() {
            if (size == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            int val = data[front];
            front = (front + 1) % data.length;
            size--;
            return val;
        }

        int peek() {
            if (size == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            return data[front];
        }
    }

    public static class QueueToStackAdapterpushEfficient {
        Queue<Integer> mainQ;
        Queue<Integer> helperQ;

        public QueueToStackAdapterpushEfficient() {
            mainQ = new ArrayDeque<>();
            helperQ = new ArrayDeque<>();
        }

        int size() {
            return mainQ.size();
        }

        void push(int val) {
            mainQ.add(val);
        }

        int pop() {
            if (mainQ.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            while (mainQ.size() > 1) {
                helperQ.add(mainQ.remove());
            }
            int top = mainQ.remove();

            while (helperQ.size() > 0) {
                mainQ.add(helperQ.remove());
            }

            return top;
        }

        int top() {
            if (mainQ.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            while (mainQ.size() > 1) {
                helperQ.add(mainQ.remove());
            }
            int top = mainQ.remove();
            helperQ.add(top);
            while (helperQ.size() > 0) {
                mainQ.add(helperQ.remove());
            }
            return top;
        }
    }

    public static class QueueToStackAdapterpopEfficient {
        Queue<Integer> mainQ;
        Queue<Integer> helperQ;

        public QueueToStackAdapterpopEfficient() {
            mainQ = new ArrayDeque<>();
            helperQ = new ArrayDeque<>();
        }

        int size() {
            return mainQ.size();
        }

        void push(int val) {
            helperQ.add(val);

            while (mainQ.size() > 0) {
                helperQ.add(mainQ.remove());
            }

            while (helperQ.size() > 0) {
                mainQ.add(helperQ.remove());
            }
        }

        int pop() {
            return mainQ.remove();
        }

        int top() {
            return mainQ.peek();
        }
    }

    public static class StackToQueueAdapterpushEfficient {
        Stack<Integer> mainS;
        Stack<Integer> helperS;

        public StackToQueueAdapterpushEfficient() {
            mainS = new Stack<>();
            helperS = new Stack<>();
        }

        int size() {
            return mainS.size();
        }

        void add(int val) {
            mainS.add(val);
        }

        int remove() {
            if (mainS.size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            while (mainS.size() > 1) {
                helperS.push(mainS.pop());
            }
            int temp = mainS.pop();

            while (helperS.size() > 0) {
                mainS.push(helperS.pop());
            }

            return temp;
        }

        int peek() {
            if (mainS.size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            while (mainS.size() > 1) {
                helperS.push(mainS.pop());
            }
            int temp = mainS.pop();
            helperS.add(temp);

            while (helperS.size() > 0) {
                mainS.push(helperS.pop());
            }

            return temp;
        }
    }

    public static class StackToQueueAdapter {
        Stack<Integer> mainS;
        Stack<Integer> helperS;

        public StackToQueueAdapter() {
            mainS = new Stack<>();
            helperS = new Stack<>();
        }

        int size() {
            return mainS.size();
        }

        void add(int val) {

            while (mainS.size() > 0) {
                helperS.push(mainS.pop());
            }

            helperS.push(val);

            while (helperS.size() > 0) {
                mainS.push(helperS.pop());
            }
        }

        int remove() {
            if (mainS.size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            return mainS.pop();
        }

        int peek() {
            if (mainS.size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            return mainS.peek();
        }
    }

    public static class TwoStack {
        int[] data;
        int tos1;
        int tos2;

        public TwoStack(int cap) {
            data = new int[cap];
            tos1 = -1;
            tos2 = data.length;
        }

        int size1() {
            return tos1 + 1;
        }

        int size2() {
            return data.length - tos2;
        }

        void push1(int val) {
            if (tos2 == tos1 + 1) {
                System.out.println("Stack overflow");
                return;
            }

            tos1++;
            data[tos1] = val;
        }

        void push2(int val) {
            if (tos2 == tos1 + 1) {
                System.out.println("Stack overflow");
                return;
            }

            tos2--;
            data[tos2] = val;
        }

        int pop1() {
            if (size1() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            int temp = data[tos1];
            tos1--;
            return temp;
        }

        int pop2() {
            if (size2() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            int temp = data[tos2];
            tos2++;
            return temp;
        }

        int top1() {
            if (size1() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            return data[tos1];
        }

        int top2() {
            if (size2() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            return data[tos2];
        }
    }

    

}
