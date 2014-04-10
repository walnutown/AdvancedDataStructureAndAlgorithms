import org.junit.Test;

public class TernarySearch {
   /*
    * Ternary search algorithm is used to find the maximum or minimum in a unimodal funciton
    * (increasing and then decreasing, or descreasing and then increasing).
    * Basic idea is to use divide and conquer. By choosing two pivots, we can divide the array
    * into 3 parts, then rule out one impossible part and shrink our search into the remaining
    * 2 parts.
    */

   public int getMax(int[] A) throws Exception {
      if (A == null || A.length == 0)
         throw new Exception("invalid input");
      return ternarySearch(A, 0, A.length - 1);
   }

   // time: O(lgn); sapce: O(1)
   private int ternarySearch(int[] A, int start, int end) {
      while (start < end) {
         int leftPivot = start + (end - start) / 3;
         int rightPivot = end - (end - start) / 3;
         if (A[leftPivot] < A[rightPivot])
            start = leftPivot + 1;
         else
            end = rightPivot - 1;
      }
      // start == end
      return A[start];
   }

   @Test
   public void test() throws Exception {
      int[] A = new int[] { 1, 2, 3, 4, 5, 3, 1, 0 };
      System.out.println(getMax(A));
   }

}