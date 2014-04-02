/*
 * Node class, val is the name of node
 */
public class Node {
   int val;

   public Node(int val) {
      this.val = val;
   }

   public Node(Node n) {
      val = n.val;
   }

   public String toString() {
      return val+"";
   }
}