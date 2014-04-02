import java.util.ArrayList;

/*
 * Graph Implementation
 */
public class Graph {
   Node root = null;
   boolean isDirected;
   private int size;
   private ArrayList<Node> list;
   // store the value of edges between two nodes
   // if there're no edges between two nodes, the value is 0
   private double[][] adjMatrix;

   public Graph(int s, Node r, boolean isd) {
      root = r;
      size = s;
      isDirected = isd;
      list = new ArrayList<Node>();
      list.add(root);
      adjMatrix = new double[size][size];
   }

   public void addNode(Node n) {
      if (this.contains(n))
         try {
            throw new Exception("Node is already in the graph");
         }
         catch (Exception e) {
            e.printStackTrace();
         }
      list.add(n);
   }

   /**
    * Remove the node, and relevant edges
    */
   public void removeNode(Node n) {
      int ni = list.indexOf(n);
      if (ni < 0)
         throw new NullPointerException("Node not found");
      list.remove(n);
      for (int j = 0; j < list.size(); j++) {
         if (adjMatrix[ni][j] > 0) {
            adjMatrix[ni][j] = 0;
            if (!isDirected)
               adjMatrix[j][ni] = 0;
         }
      }
   }

   public void addEdge(Node p, Node q, double cost) {
      int pi = list.indexOf(p);
      int qi = list.indexOf(q);

      if (p.equals(root)) {
         pi = list.indexOf(root);
      }
      if (q.equals(root)) {
         qi = list.indexOf(root);
      }

      if (pi < 0 || qi < 0)
         throw new NullPointerException("Node not found");
      adjMatrix[pi][qi] = cost;
      if (!isDirected)
         adjMatrix[qi][pi] = cost;
   }

   public ArrayList<Node> getNeighbors(Node n) {
      int ni = list.indexOf(n);
      ArrayList<Node> adj = new ArrayList<Node>();
      if (ni < 0)
         throw new NullPointerException("Node not found");
      for (int j = 0; j < list.size(); j++) {
         if (adjMatrix[ni][j] > 0)
            adj.add(list.get(j));
      }
      return adj;
   }

   public double getEdgeCost(Node p, Node q) {
      int pi = list.indexOf(p);
      int qi = list.indexOf(q);
      if (pi < 0 || qi < 0)
         throw new NullPointerException("Node not found");
      return adjMatrix[pi][qi];
   }

   public ArrayList<Node> getNodeList() {
      return list;
   }

   public boolean contains(Node target) {
      for (int i = 0; i < list.size(); i++) {
         if (target.equals(list.get(i)))
            return true;
      }
      return false;
   }
}
