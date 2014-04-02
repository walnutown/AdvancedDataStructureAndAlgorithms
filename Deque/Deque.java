import org.junit.Test;

/*
 * implementation of double ended queue
 * basic operations: addFirst, addLast, pollFirst, pollLast, peekFist, peekLast
 */
public class Deque {
   private DoublyListNode head, tail;

   public Deque() {
      head = null;
      tail = null;
   }

   public boolean isEmpty() {
      return head == null;
   }

   public void addFirst(int val) {
      if (head == null) {
         head = new DoublyListNode(val);
         tail = head;
      } else {
         DoublyListNode node = new DoublyListNode(val);
         node.next = head;
         head.prev = node;
         head = node;
      }
   }

   public void addLast(int val) {
      if (tail == null) {
         tail = new DoublyListNode(val);
         head = tail;
      } else {
         DoublyListNode node = new DoublyListNode(val);
         node.prev = tail;
         tail.next = node;
         tail = node;
      }
   }

   public int pollFirst() {
      DoublyListNode node = head;
      if (head == tail) {
         head = null;
         tail = null;
      } else {
         head = head.next;
         head.prev = null;
      }
      return node.val;
   }

   public int peekFirst() {
      if (this.isEmpty())
         throw new NullPointerException("Deque is empty");
      return head.val;
   }

   public int pollLast() {
      DoublyListNode node = tail;
      if (head == tail) {
         head = null;
         tail = null;
      } else {
         tail = tail.prev;
         tail.next = null;
      }
      return node.val;
   }

   public int peekLast() {
      if (this.isEmpty())
         throw new NullPointerException("Deque is empty");
      return tail.val;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      DoublyListNode p = head;
      while (p != null) {
         sb.append(p + ", ");
         p = p.next;
      }
      if (head != null)
         sb.delete(sb.length() - 2, sb.length());
      sb.append("]");
      return sb.toString();
   }

   private class DoublyListNode {
      int val;
      DoublyListNode prev;
      DoublyListNode next;

      public DoublyListNode(int val) {
         this.val = val;
      }

      public String toString() {
         return val + "";
      }
   }

   @Test
   public void testAddAndPollFisrt() {
      Deque deque = new Deque();
      System.out.println("Test addFisrt()");
      for (int i = 0; i < 10; i++) {
         deque.addFirst(i);
         System.out.println(deque);
      }
      while (!deque.isEmpty()) {
         deque.pollFirst();
         System.out.println(deque);
      }
   }

   @Test
   public void testAddAndPollLast() {
      Deque deque = new Deque();
      System.out.println("Test addLast()");
      for (int i = 0; i < 10; i++) {
         deque.addLast(i);
         System.out.println(deque);
      }
      while (!deque.isEmpty()) {
         deque.pollLast();
         System.out.println(deque);
      }
   }
   
   @Test
   public void testIsEmpty(){
      Deque deque = new Deque();
      System.out.println("Test isEmpty()");
      System.out.println(deque.isEmpty());
      deque.addFirst(0);
      System.out.println(deque.isEmpty());
   }

}
