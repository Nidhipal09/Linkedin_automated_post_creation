package LinkedLists;

public class LinkedList {
    Node head;
    Node tail;
    int size;

    public static class Node {
        int data;
        Node next;
    }

    void addLast(int val) {
        Node node = new Node();
        node.data = val;
        node.next = null;

        if (size == 0) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void display() {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void removeFirst() {
        if (size == 0) {
            System.out.println("List is empty");
        } else if (size == 1) {
            head = tail = null;
            size = 0;
        } else {
            head = head.next;
            size--;
        }
    }

    public int getFirst() {
        if (size == 0) {
            System.out.println("List is empty");
            return -1;
        } else {
            return head.data;
        }
    }

    public int getLast() {
        if (size == 0) {
            System.out.println("List is empty");
            return -1;
        } else {
            return tail.data;
        }
    }

    public int getAt(int idx) {
        if (size == 0) {
            System.out.println("List is empty");
            return -1;
        } else if (idx < 0 || idx >= size) {
            System.out.println("Invalid arguments");
            return -1;
        }

        Node temp = head;
        for (int i = 1; i <= idx; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    public void addFirst(int val) {
        Node temp = new Node();
        temp.data = val;

        if (size == 0) {
            head = tail = temp;
        } else {
            temp.next = head;
            head = temp;
        }

        size++;
    }
   
    public void addAt(int idx, int val){
        if(idx <0 || idx> size){
          System.out.println("Invalid arguments");
          return;
        }
        Node node = new Node();
        node.data = val;
        
        if(idx == 0){
          node.next = head;
          head=node;
        }
        else if(idx == size){
          tail.next = node;
          tail=node;
        }
        else{
          Node temp = head; 
          for(int i=1; i<idx; i++){
              temp = temp.next;
          }  
          Node nnode = temp.next;
          temp.next=node;
          node.next=nnode;
        }
        size++;
      }

      public void removeLast(){
        if (size == 0) {
          System.out.println("List is empty");
        } else if (size == 1) {
          head = tail = null;
          size = 0;
        } else {
          Node temp=head;
          while(temp.next!=tail){
              temp=temp.next;
          }
          temp.next=null;
          tail=temp;
          size--;
        }
      }

      public void removeAt(int idx) {
        if (idx < 0 || idx >= size) {
          System.out.println("Invalid arguments");
        } else if (idx == 0) {
          removeFirst();
        } else if (idx == size-1) {
          removeLast();
        } else {
          
          Node temp = head;
          for (int i = 1; i < idx; i++) {
            temp = temp.next;
          }
          Node nnode = temp.next.next;
          temp.next=nnode;
          size--;
        }
      }

      
}
