package HashmapAndHeap;


import java.util.*;


public class PriorityQueueExplained {
    public static void main(String[] args) throws Exception{
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());

        int[] arr={3,67,23,1,2,43,87,32,17};

        for(int val: arr){
            pq.add(val);
        }

        while(pq.size()>0){
            System.out.println(pq.peek());
            pq.remove();
        }
    }
}
