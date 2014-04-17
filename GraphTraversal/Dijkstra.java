
public class Dijkstra {
   /*
    * Given an weighted undirected/directed graph and a source vertex in graph, find shortest paths from source to all vertices in
    * the given graph. (all edge weights are non-negative)
    */
   
   
   // Maintain two minHeap<Path>, open and closed
   // Path stores the nodes and cost from src node to dst node
   // We traverse the graph until the open heap is empty
   // In the expanding step
   // We choose the min path from open heap and the current node is its tail node 
   // [1] get all adjacent nodes of current node
   // [2] if the adjacent node is not in open or closed queue, put it in open heap;
   //     else if the adjacent node is in the paths of open heap, update the path
   //     else if the adjacent node is in the paths of closed heap, update the path and add to the open heap
   // time: if heap is used, O((E+V)*lgV) ; otherwise O((E+V)*V); space: O(V)
   
   
}
