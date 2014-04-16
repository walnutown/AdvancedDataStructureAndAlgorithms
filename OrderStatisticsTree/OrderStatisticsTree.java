import org.junit.Test;

public class OrderStatisticsTree {

   /*
    * An order statistic tree is a variant of the binary search tree (or more generally, a
    * B-tree) that supports two additional operation beyond insertion, lookup and deletion:
    * Select(i) â find the i'th smallest element stored in the tree
    * Rank(x) , find the rank of element x in the tree, i.e. its index in the sorted list of
    * elements of the tree.
    * Both operations can be performed in O(log n) time in the average case if the tree is balanced
    */
   // http://en.wikipedia.org/wiki/Order_statistic_tree

   OrderStatisticsTreeNode root;

   public OrderStatisticsTree() {
      root = null;
   }

   // The difference with insert in normal BST is that we have to update 'size'
   public void insert(int val) {
      if (root == null) {
         root = new OrderStatisticsTreeNode(val);
         return;
      }
      OrderStatisticsTreeNode curr = root;
      while (true) {
         curr.size++;
         if (curr.val < val) {
            if (curr.right != null) {
               curr = curr.right;
            } else {
               curr.right = new OrderStatisticsTreeNode(val);
               break;
            }
         } else {
            if (curr.left != null) {
               curr = curr.left;
            } else {
               curr.left = new OrderStatisticsTreeNode(val);
               break;
            }
         }
      }
   }

   public OrderStatisticsTreeNode select(int rank) {
      if (rank < 1)
         throw new IllegalArgumentException("Invalid rank");
      OrderStatisticsTreeNode curr = root;
      while (curr != null) {
         int currRank = curr.left == null ? 1 : curr.left.size + 1;
         if (rank == currRank)
            return curr;
         else if (rank < currRank)
            curr = curr.left;
         else {
            curr = curr.right;
            rank = rank - currRank;
         }
      }
      return null;
   }

   public int rank(int val) {
      OrderStatisticsTreeNode curr = root;
      while (curr != null) {
         int currRank = curr.left == null ? 1 : curr.left.size + 1;
         if (curr.val == val)
            return currRank;
         else if (curr.val > val)
            curr = curr.left;
         else
            curr = curr.right;
      }
      return -1;
   }

   public class OrderStatisticsTreeNode {
      int val;
      int size;
      OrderStatisticsTreeNode left;
      OrderStatisticsTreeNode right;

      public OrderStatisticsTreeNode(int val) {
         this.val = val;
         size = 1;
      }

      public String toString() {
         return val + "";
      }
   }

   @Test
   public void test() {
      OrderStatisticsTree tree = new OrderStatisticsTree();
      tree.insert(10);
      tree.insert(4);
      tree.insert(14);
      tree.insert(8);
      tree.insert(6);
      tree.insert(2);
      tree.insert(12);
      
      System.out.println(tree.select(1));
      System.out.println(tree.rank(10));
   }
}