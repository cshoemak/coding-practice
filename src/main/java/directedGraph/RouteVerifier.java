package directedGraph;

import java.util.LinkedList;
import java.util.List;

public class RouteVerifier
{
  public boolean verifyRoute(DirectedGraph graph, int from, int to)
  {
    if (graph == null || graph.getSize() == 0 || from < 0 
        || from >= graph.getSize() || to < 0 || to >= graph.getSize())
    {
      throw new IllegalArgumentException("Non-null graph and valid vertexes for from and to required.");
    }

    LinkedList<Integer> bfsQ = new LinkedList<Integer>();
    boolean[] discovered = new boolean[graph.getSize()];
    bfsQ.add(Integer.valueOf(from));
    discovered[from] = true;
    while (!bfsQ.isEmpty())
    {
      List<Integer> edges = graph.getEdges(Integer.valueOf(bfsQ.poll()));
      if (edges == null)
      {
        throw new RuntimeException("Expected vertex did not exist. Graph may be inconsistent or _from_ hasn't been added yet.");
      }
      for (Integer edge : edges)
      {
        int val = edge.intValue();
        if (val < 0 || val >= graph.getSize())
        {
          throw new RuntimeException("Graph may be inconsistent. Found an invalid edge.");
        }
        if (discovered[val])
        {
          continue;
        }
        discovered[val] = true;
        bfsQ.add(edge);
      }
    }
    return discovered[to];
  }

  public static void main(String[] args)
  {
    DirectedGraph graph = new DirectedGraph(10);
    graph.addVertex(1);
    graph.addVertex(2);
    graph.addEdge(1, 2);
    RouteVerifier verifier = new RouteVerifier();
    boolean verified = verifier.verifyRoute(graph, 1, 2);
    System.out.println("Verified 1->2: " + verified);
    verified = verifier.verifyRoute(graph, 2, 1);
    System.out.println("Verified 2->1: " + verified);
    // Circular Graph 1->2->3->1
    graph.addVertex(3);
    graph.addEdge(2, 3);
    graph.addEdge(3, 1);
    verified = verifier.verifyRoute(graph, 1, 2);
    System.out.println("Verified 1->2: " + verified);
    verified = verifier.verifyRoute(graph, 2, 1);
    System.out.println("Verified 2->1: " + verified);

    graph.addVertex(4);
    graph.addEdge(3, 4);
    graph.addVertex(5);
    graph.addEdge(4, 5);
    graph.addVertex(6);
    graph.addEdge(6, 5);
    graph.addVertex(7);
    graph.addEdge(1, 7);
    graph.addVertex(8);
    graph.addEdge(2, 8);

    verified = verifier.verifyRoute(graph, 1, 5);
    System.out.println("Verified 1->5: " + verified);
    verified = verifier.verifyRoute(graph, 1, 6);
    System.out.println("Verified 1->6: " + verified);
    verified = verifier.verifyRoute(graph, 1, 8);
    System.out.println("Verified 1->8: " + verified);
  }
}
