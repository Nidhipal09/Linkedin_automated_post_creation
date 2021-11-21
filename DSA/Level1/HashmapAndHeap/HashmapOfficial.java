package HashmapAndHeap;
import java.util.*;

public class HashmapOfficial {
    public static class HashMap<K, V> {
        private class HMNode {
          K key;
          V value;
    
          HMNode(K key, V value) {
            this.key = key;
            this.value = value;
          }
        }
    
        private int size; // n
        private LinkedList<HMNode>[] buckets; // N = buckets.length
    
        public HashMap() {
          initbuckets(4);
          size = 0;
        }
    
        private void initbuckets(int N) {
          buckets = new LinkedList[N];
          for (int bi = 0; bi < buckets.length; bi++) {
            buckets[bi] = new LinkedList<>();
          }
        }
    
        public void put1(K key, V value) throws Exception {
          int bi = hash(key);
          int di = getIndexWithinBucket(key, bi);
          if(di==-1){
            buckets[bi].add(new HMNode(key, value));  
            size++;
          }else{
            buckets[bi].get(di).value = value;  
            
          }
          
          
          double lambda = (size * 1.0)/buckets.length;
          if(lambda > 2.0){
              rehash();
          }
        }
        
        private int hash(K key){
            int hc=key.hashCode();
            return Math.abs(hc) % buckets.length;
        }
        
        private int getIndexWithinBucket(K key, int bi){
            int di=0;
            for(HMNode node: buckets[bi]){
                if(node.key.equals(key)) return di;
                di++;
            }
            
            return -1;
        }
        
        public void rehash() throws Exception{
            LinkedList<HMNode>[] oba=buckets;
            initbuckets(oba.length*2);
            size=0;
            
            for(int i=0; i<oba.length; i++){
                for(HMNode node: oba[i]){
                   put1(node.key, node.value); 
                }
            }
        }
    
        public V get1(K key) throws Exception {
          int bi = hash(key);
          int di = getIndexWithinBucket(key, bi);
          if(di==-1){
            return null; 
          }else{
            return buckets[bi].get(di).value;  
          }
        }
    
        public boolean containsKey1(K key) {
          int bi = hash(key);
          int di = getIndexWithinBucket(key, bi);
          
          if(di==-1){
            return false;
          }else{
            return true;
          }
        }
    
        public V remove1(K key) throws Exception {
          int bi = hash(key);
          int di = getIndexWithinBucket(key, bi);
          if(di==-1){
            return null;
          }else{
            HMNode node= buckets[bi].remove(di);  
            size--;
            return node.value;
          }
        }
    
        public ArrayList<K> keyset1() throws Exception {
            ArrayList<K> keys= new ArrayList<>();   
            
           for(LinkedList<HMNode> ll:buckets){
               for(HMNode node :ll){
                keys.add(node.key);   
               }
           }
           
           return keys;
        }
    
        public int size() {
          return size;
        }
    
       
    }

}
