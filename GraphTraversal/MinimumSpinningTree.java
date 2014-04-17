public class MinimumSpinningTree {
   /*
    * A spanning tree of that graph is a subgraph that is a tree and connects all the vertices
    * together. A minimum spanning tree (MST) or minimum weight spanning tree is then a spanning
    * tree with weight less than or equal to the weight of every other spanning tree
    */

   // There're two algorithms to find MST of the graph, Prim and Kruskal, both are greedy algorithms
   
   // In Kruskal, each time pick the least weight edge and add it to the connected set, until all
   // the vertices are connected
   
   // In Prim, which is similar to Dijkstra. The difference mainly lies in the expanding step
   // We also maintain two min heaps, open and closed. Yet we store edges in the heap,
   // instead of paths. And in the expanding step, we choose the edge with min vlaue from the heap.
   // This edge should not create a cycle in the connected spinning tree.
   // time: O((V+E)*lgV); space: O(V)
}
