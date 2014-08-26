package directedGraph;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph
{
  private List<Integer>[] graph;

  public DirectedGraph(int size)
  {
    graph = (ArrayList<Integer>[]) (new ArrayList<?>[size]);
  }

  private void validateVertex(int vertex)
  {
    if (vertex < 0 || vertex >= graph.length)
    {
      throw new IllegalArgumentException("Vertex position invalid. Must be between 0 and " + (graph.length - 1));
    }
  }

  public void addVertex(int vertex)
  {
    validateVertex(vertex);
    if (graph[vertex] == null)
    {
      graph[vertex] = new ArrayList<Integer>();
    }
  }

  public void addEdge(int from, int to)
  {
    validateVertex(from);
    validateVertex(to);
    List<Integer> edges = graph[from];
    if (edges == null || graph[to] == null)
    {
      throw new IllegalArgumentException("Vertices must exist prior to creating an edge between them.");
    }

    if (!edges.contains(Integer.valueOf(to)))
    {
      edges.add(Integer.valueOf(to));
    }
  }

  public List<Integer> getEdges(int vertex)
  {
    validateVertex(vertex);
    return graph[vertex];
  }

  public int getSize()
  {
    return graph.length;
  }
}
