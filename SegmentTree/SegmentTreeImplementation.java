import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Test;

/*
 * A segment tree is a heap-like data structure that can be used for making update/query operations
 * upon array intervals in logarithmical time.
 * We can query either index or value.
 * [1] Assume the node holds the interval [i,j] and i<j. Its left and right son will hold
 * the information for the intervals [i, (i+j)/2] and [(i+j)/2+1, j]
 * [2] the height of a segment tree for an interval with N elements is [logN]+1
 * [3] has the same structure as a heap, if current node's index is x, its left child's index
 * would be 2*x, and the index of right child would be 2*x+1
 */

// In the following implementation, we build a segment tree of range sum
public class SegmentTreeImplementation {

   public class SegmentTree {
      private int[] ST;
      private int[] A;

      public SegmentTree(int[] A) {
         this.A = Arrays.copyOfRange(A, 0, A.length);
         int treeSize = 2 * A.length - 1;
         ST = new int[treeSize];
         builder(this.A, 0, A.length - 1, 0);
      }

      /**
       * Use recursion to build the tree
       * time: O(n)
       * 
       * @param as, ae are the starting index and ending index of the range in A
       * @param si is the index of the node (representing the segment range) in ST
       */
      private int builder(int[] A, int as, int ae, int si) {
         // terminate condition
         if (as == ae) {
            ST[si] = A[as];
            return A[as];
         }
         int mid = (as + ae) >> 1;
         ST[si] = builder(A, as, mid, si * 2 + 1) + builder(A, mid + 1, ae, si * 2 + 2);
         return ST[si];
      }

      /**
       * Use recursion to get the node value.
       * Combine as/ae, qs/qe to get the terminating condition
       * time: O(lgn)
       * 
       * @param qs, qe are the starting and ending index of the qeruy range
       */
      public int getSum(int qs, int qe) {
         if (qs < 0 || qe > A.length - 1 || qs > qe)
            throw new NoSuchElementException("Invalid range query");
         return getSumHelper(0, 0, A.length - 1, qs, qe); // start from the root
      }

      private int getSumHelper(int si, int as, int ae, int qs, int qe) {
         // If segment of this node is a part of query range, then return the
         // sum of the segment
         if (qs <= as && ae <= qe)
            return ST[si];
         // If segment of this node is out of the query range, sum is 0
         if (ae < qs || as > qe)
            return 0;
         // If a part of this segment overlaps with the query range
         int mid = (as + ae) >> 1;
         return getSumHelper(2 * si + 1, as, mid, qs, qe) + getSumHelper(2 * si + 2, mid + 1, ae, qs, qe);

      }

      /**
       * Update the value of an element in the segment tree with given index
       * We update the sum value by adding diff of newValue and oldValue
       * time: O(lgn)
       */
      public void updateValue(int index, int newValue) {
         if (index < 0 || index > A.length)
            throw new NoSuchElementException("Index out of bound");
         // Get the difference between new value and old value
         int diff = newValue - A[index];
         // update the elment array
         A[index] = newValue;
         // update the values of nodes in segment tree
         updateValueHelper(0, 0, A.length - 1, index, diff);
      }

      private void updateValueHelper(int si, int as, int ae, int index, int diff) {
         if (as > ae || index < as || index > ae)
            return;
         ST[si] += diff;
         if (as == ae)
            return;
         int mid = (as + ae) >> 1;
         updateValueHelper(2 * si + 1, as, mid, index, diff);
         updateValueHelper(2 * si + 2, mid + 1, ae, index, diff);
      }

      public String printRange(int qs, int qe) {
         if (qs < 0 || qe > A.length)
            throw new NoSuchElementException("Qeury index is out of range");
         int[] range = Arrays.copyOfRange(A, qs, qe + 1);
         return "Range: " + Arrays.toString(range);
      }

      public String toString() {
         return "SegmentTree: " + Arrays.toString(ST);
      }
   }

   @Test
   public void testBuildTree() {
      int[] A = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
      System.out.println("testBuildTree()");
      SegmentTree st = new SegmentTree(A);
      System.out.println(st.toString());
   }

   @Test
   public void testGetSum() {
      int[] A = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
      SegmentTree st = new SegmentTree(A);
      System.out.println("testGetSum()");
      System.out.println(st.printRange(0, 5));
      System.out.println(st.getSum(0, 5));
   }

   @Test
   public void testUpdateValue() {
      int[] A = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
      SegmentTree st = new SegmentTree(A);
      System.out.println("testUpdateValue()");
      System.out.println(st.printRange(0, 5));
      System.out.println("Sum: " + st.getSum(0, 5));
      st.updateValue(1, 20);
      System.out.println(st.toString());
      System.out.println(st.printRange(0, 5));
      System.out.println("Sum: " + st.getSum(0, 5));
   }

}
