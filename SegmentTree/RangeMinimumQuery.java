/*Range Minimum Query(MRQ) is a famous application of segment tree. It's used
on arrays to find the position of an element with the minimum value between
two specified indices. We'll start from the trivial algorithm.
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
// time: f(n) = O(n^2), g(n) = O(1); sapce: O(n^2)
int[][] process(int[] A){
	int N = A.length;
	int[][] M = new int[N][N];
	for (int i = 0; i<N; i++){
		M[i][i] = i;
	}
	for (int i=0; i<N; i++){
		for (int j=i+1; j<N; j++){
			if (M[i][j-1]>A[j])
				M[i][j] = j; // note, we store index here, not value
			else
				M[i][j] = M[i][j-1];
		}
	}
	return M;
}

// SOL3:
// Use segment tree, see SegmentTreeImplementation.java
// time: f(n) = O(n), g(n) = O(lgn); space: O(2*n-1)





