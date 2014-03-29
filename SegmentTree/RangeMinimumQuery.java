import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Test;

public class RangeMinimumQuery {
   /*
    * Range Minimum Query(MRQ) is a famous application of segment tree. It's used
    * on arrays to find the position of an element with the minimum value between
    * two specified indices. We'll start from the trivial algorithm.
    */

   // The notation for preprocessing is f(n) and query time g(n).

   // SOL1:
   // Pre-process the array to reduce the query time. For ever pair of
   // indices (i,j), we store the value of RMQ(i,j) in a table M[N][N]. We need 3
   // loops, 2 loops to decide the starting and ending indices of the range, and
   // the 3rd loop to traverse the sub-array to find the minimum.
   // time: f(n)=O(n^3), g(n)= O(1); space: O(n^2)

   // SOL2:
   // time-optimized version of SOL1, use dynamic programming.
   // time: f(n) = O(n^2), g(n) = O(1); space: O(n^2)
   int[][] process(int[] A) {
      int N = A.length;
      int[][] M = new int[N][N];
      for (int i = 0; i < N; i++) {
         M[i][i] = i;
      }
      for (int i = 0; i < N; i++) {
         for (int j = i + 1; j < N; j++) {
            if (M[i][j - 1] > A[j])
               M[i][j] = j; // note, we store index here, not value
            else
               M[i][j] = M[i][j - 1];
         }
      }
      return M;
   }

   // SOL3:
   // Use segment tree, see SegmentTreeImplementation.java
   // time: f(n) = O(n), g(n) = O(lgn); space: O(2*n-1)

   // SOL4:
   // Partition the array into sub-arrays, each array's size is sqrt(n)
   // Pre-process the minimum value of each sub-array, this takes O(n)
   // When we do the query, use the pre-processed the minimum value,
   // this takes at most O(3sqrt(n))
   // time: f(n) = O(n), g(n) = O(sqrt(n)); space: O(sqrt(n))

   int[] process2(int[] A) {
      int N = A.length;
      int size = (int) Math.sqrt(N);
      int[] M = new int[N/size+1];
      int min = 0;
      for (int i = 0; i < N; i++) {
         if (i % size == 0)
            min = A[i];
         else
            min = Math.min(min, A[i]);
         M[i/size] = min;
      }
      return M;
   }

   int query(int i, int j, int[] M, int[] A) {
      if (i > j || i < 0 || j >= A.length)
         throw new NoSuchElementException("Invalid range query");
      System.out.println(Arrays.toString(Arrays.copyOfRange(A, i, j+1)));
      if (i == j)
         return A[i];
      int size = (int) Math.sqrt(A.length);
      int l = i / size; // l, r is the left and right index of covered sub-arrays in M
      int r = j / size;
      int min = Integer.MAX_VALUE;
      if (l==r){
         for (int k=i; k<=j; j++)
            min = Math.min(min, A[k]);
         return min;
      }
      while (i < (l+1) * size)
         min = Math.min(min, A[i++]);
      while (j >= r * size)
         min = Math.min(min, A[j--]);
      for (int k = l+1; k <r; k++)
         min = Math.min(min, M[k]);
      return min;
   }

   @Test
   public void testSolution4() {
      int[] A = new int[] { 1, 2, 3, 4, 5, 6, 7, -1 };
      int[] M = process2(A);
      for (int i=0; i<A.length;i++){
         for (int j=i; j<A.length; j++)
            System.out.println(query(i, j, M, A));
      }
   }
}
