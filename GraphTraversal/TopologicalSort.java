import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

public class TopologicalSort {
   /*
    * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such
    * that for every directed edge u-v, vertex u comes before v in the ordering. Topological Sorting
    * for a graph is not possible if the graph is not a DAG.
    */

   // DFS + reverse. 
   // [1] Basically post-order DFS, and then reverse the order of the result 
   // [2] Need to DFS starting from all the nodes in graph, in case the graph is disconnected
   // [3] Maintain a visited array to avoid revisiting.
   // time: O(V+E); space: O(V)
   // refer: http://www.geeksforgeeks.org/topological-sorting/
   public ArrayList<Node> topologicalSort(Graph graph) {
      ArrayList<Node> res = new ArrayList<Node>();
      ArrayList<Node> nodes = graph.getNodeList();
      Set<Node> visited = new HashSet<Node>();
      for (Node node : nodes) {
         if (!visited.contains(node))
            dfs(graph, node, res, visited);
      }
      Collections.reverse(res);
      return res;
   }

   private void dfs(Graph graph, Node node, ArrayList<Node> res, Set<Node> visited) {
      visited.add(node);
      ArrayList<Node> neighbors = graph.getNeighbors(node);
      if (!neighbors.isEmpty()) {
         for (Node adj : neighbors) {
            if (!visited.contains(adj)) {
               dfs(graph, adj, res, visited);
            }
         }
      }
      res.add(node);
   }

   @Test
   public void test() {
      Node node0 = new Node(0);
      Node node1 = new Node(1);
      Node node2 = new Node(2);
      Node node3 = new Node(3);
      Node node4 = new Node(4);
      Node node5 = new Node(5);
      Graph graph = new Graph(6, node5, true);
      graph.addNode(node0);
      graph.addNode(node1);
      graph.addNode(node2);
      graph.addNode(node3);
      graph.addNode(node4);
      graph.addEdge(node5, node2, 1);
      graph.addEdge(node5, node0, 1);
      graph.addEdge(node4, node0, 1);
      graph.addEdge(node4, node1, 1);
      graph.addEdge(node2, node3, 1);
      graph.addEdge(node3, node1, 1);
      System.out.println(topologicalSort(graph));
   }
}
