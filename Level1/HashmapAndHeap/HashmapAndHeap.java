package HashmapAndHeap;

import java.util.*;

public class HashmapAndHeap {
    public static Character highestFreqChar(String s) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (hm.containsKey(ch)) {
                int freq = hm.get(ch);
                hm.put(ch, freq + 1);
            } else {
                hm.put(ch, 1);
            }
        }

        char mfc = s.charAt(0);

        for (Character key : hm.keySet()) {
            if (hm.get(key) > hm.get(mfc)) {
                mfc = key;
            }
        }

        return mfc;
    }

    public static void getCommonElements(int[] a1, int[] a2) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < a1.length; i++) {

            if (!hm.containsKey(a1[i])) {
                hm.put(a1[i], 1);
            }
        }

        for (int i = 0; i < a2.length; i++) {
            if (hm.containsKey(a2[i])) {
                System.out.println(a2[i]);
                hm.remove(a2[i]);
            }
        }

    }

    public static void getCommonElements2(int[] a1, int[] a2) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < a1.length; i++) {

            if (!hm.containsKey(a1[i])) {
                hm.put(a1[i], 1);
            } else {
                int freq = hm.get(a1[i]);
                hm.put(a1[i], freq + 1);
            }
        }

        for (int i = 0; i < a2.length; i++) {
            if (hm.containsKey(a2[i])) {
                int freq = hm.get(a2[i]);
                if (freq == 0)
                    hm.remove(a2[i]);
                else {
                    System.out.println(a2[i]);
                    hm.put(a2[i], freq - 1);
                }
            }
        }

    }

    public static void longestConsecutiveSS(int[] arr) {
        HashMap<Integer, Boolean> hm = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            hm.put(arr[i], false);
        }

        for (int i = 0; i < arr.length; i++) {
            if (!hm.containsKey(arr[i] - 1)) {
                hm.put(arr[i], true);
            }
        }

        int lssl = 0;
        int lss = 0;
        for (int val : arr) {
            if (hm.get(val)) {
                int ssl = 1;
                int ss = val;

                while (hm.containsKey(ss + ssl)) {
                    ssl++;
                }

                if (ssl > lssl) {
                    lssl = ssl;
                    lss = ss;
                }
            }
        }

        for (int i = 0; i < lssl; i++) {
            System.out.println(lss + i);
        }
    }

    public static void kLargestElements(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int i = 0;
        while (i < k) {
            pq.add(arr[i]);
            i++;
        }

        int j = k;
        while (j < arr.length) {
            if (pq.peek() < arr[j]) {
                pq.remove();
                pq.add(arr[j]);
            }
            j++;
        }

        while (pq.size() > 0) {
            System.out.println(pq.remove());
        }
    }

    public static void sortkSorted(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i <= k; i++) {
            pq.add(arr[i]);
        }

        for (int i = k + 1; i < arr.length; i++) {
            System.out.println(pq.remove());
            pq.add(arr[i]);
        }

        while (pq.size() > 0) {
            System.out.println(pq.remove());
        }
    }

    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public void add(int val) {
        if (right.size() > 0 && val > right.peek())
            right.add(val);
        else
            left.add(val);

        if (left.size() - right.size() == 2) {
            right.add(left.remove());
        } else if (right.size() - left.size() == 2) {
            left.add(right.remove());
        }
    }

    public int remove() {
        if (this.size() == 0) {
            System.out.println("Underflow");
            return -1;
        } else if (left.size() >= right.size()) {
            return left.remove();
        } else {
            return right.remove();
        }

    }

    public int peek() {
        if (this.size() == 0) {
            System.out.println("Underflow");
            return -1;
        } else if (left.size() >= right.size()) {
            return left.peek();
        } else {
            return right.peek();
        }

    }

    public int size() {
        return left.size() + right.size();
    }

    public static class Pair implements Comparable<Pair> {
        int li;
        int di;
        int data;

        Pair(int li, int di, int data) {
            this.li = li;
            this.di = di;
            this.data = data;
        }

        public int compareTo(Pair o) {
            return this.data - o.data;
        }
    }

    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists) {
        ArrayList<Integer> rv = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for (int i = 0; i < lists.size(); i++) {
            pq.add(new Pair(i, 0, lists.get(i).get(0)));
        }

        while (pq.size() > 0) {
            Pair p = pq.remove();
            rv.add(p.data);
            p.di++;
            if (p.di < lists.get(p.li).size()) {
                p.data = lists.get(p.li).get(p.di);
                pq.add(p);
            }

        }
        

        return rv;
    }



    ArrayList<Integer> data=new ArrayList<>();

    public void add1(int val) {
        data.add(val);
        if(data.size()>0) upHeapify(data.size()-1);
     }
     
     public int remove1() {
         if(this.size()==0){
            System.out.println("Underflow"); 
            return -1;
         }
        swap(0, data.size()-1);
        int val = data.remove(data.size()-1);
        downHeapify(0);
         return val;
     }
 
     
     public void upHeapify(int ci){
         if(ci==0){
             return;
         }
         
         int pi = (ci-1) / 2;
         
         if(data.get(ci)<data.get(pi)){ 
             swap(pi,ci);
             upHeapify(pi);
         }
     }
     
     
     public void downHeapify(int pi){
         
         int mi = pi;
         
         int lci = 2*pi + 1;
         if(lci < data.size() && data.get(lci)<data.get(mi)) mi=lci;
         
         int rci = 2*pi + 2;
         if(rci < data.size() && data.get(rci)<data.get(mi)) mi=rci;
         
         if(mi!=pi){ 
             swap(pi,mi);
             downHeapify(mi);
         }
     }
     
     public void swap(int i, int j){
         int ith = data.get(i);
         int jth = data.get(j);
         data.set(i, jth);
         data.set(j, ith);
     }
 
     public int peek1() {
         if(data.size()==0){
            System.out.println("Underflow"); 
            return -1;
         }
       return data.get(0);
     }
 
     public int size1() {
       return data.size();
     }




     

}