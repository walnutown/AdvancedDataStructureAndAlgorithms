import java.util.Arrays;

import org.junit.Test;

public class MaxOfContinuousSubarrays {

   /*
    * Given an array and an integer k, find the maximum for each and every contiguous subarray of
    * size k.
    * Examples:
    * Input :
    * arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}
    * k = 3
    * Output :
    * 3 3 4 5 5 5 6
    * Input :
    * arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13}
    * k = 4
    * Output :
    * 10 10 10 15 15 90 90
    */
   // Maintain a deque, which hold the index of elements in descending order
   // by peekFirst, we get max of the deque; by peekLast, we get min of deque
   // time: O(n), each element is accessed at most twice; space: O(k)
   // refer http://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k/
   public int[] getMaxs(int[] A, int k) {
      // add initial k values
      assert (A.length >= k);
      int[] mins = new int[A.length - k + 1];
      Deque deque = new Deque();
      int i = 0;
      for (; i < k; i++) {
         while (!deque.isEmpty() && A[i] >= A[deque.peekLast()])
            deque.pollLast();
         deque.addLast(i);
      }
      for (; i < A.length; i++) {
         mins[i - k] = A[deque.peekFirst()]; // store the max of previous range
         if (!deque.isEmpty() && deque.peekFirst() <= i - k)
            deque.pollFirst();
         while (!deque.isEmpty() && A[i] >= A[deque.peekLast()])
            deque.pollLast();
         deque.addLast(i);

      }
      mins[i - k] = A[deque.peekFirst()];
      return mins;
   }

   @Test
   public void test() {
      int[] A = new int[] { 1, 2, 3, 1, 4, 5, 2, 3, 6 };
      System.out.println(Arrays.toString(getMaxs(A, 3)));
      int[] B = new int[] { 8, 5, 10, 7, 9, 4, 15, 12, 90, 13 };
      System.out.println(Arrays.toString(getMaxs(B, 4)));
   }

}