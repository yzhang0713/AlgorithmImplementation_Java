import java.util.*;

/**
 * This is the class representing Graph data structure
 */
public class Graph {

    private List<Edge> edgeList;

    private Map<Integer, List<AdjNode>> adjacencyList;

    private int edgeCount;

    private int nodeCount;

    // Constructor
    public Graph() {
        this.edgeList = new ArrayList<>();
        this.adjacencyList = new HashMap<>();
        this.edgeCount = 0;
        this.nodeCount = 0;
    }

    public void addEdge(Edge edge) {
        if (this.edgeList.contains(edge)) {
            return;
        }
        this.edgeList.add(edge);
        edgeCount++;
        // Handle adjacency list
        insertToAdjacentList(edge.getEdgeNodeOne(), edge.getEdgeNodeTwo(), edge.getWeight());
        insertToAdjacentList(edge.getEdgeNodeTwo(), edge.getEdgeNodeOne(), edge.getWeight());
    }

    private void insertToAdjacentList(int nodeOne, int nodeTwo, int weight) {
        if (!adjacencyList.containsKey(nodeOne)) {
            List<AdjNode> curAdjList = new ArrayList<>();
            curAdjList.add(new AdjNode(nodeTwo, weight));
            adjacencyList.put(nodeOne, curAdjList);
            nodeCount++;
        } else {
            adjacencyList.get(nodeOne).add(new AdjNode(nodeTwo, weight));
        }
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public Map<Integer, List<AdjNode>> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(Map<Integer, List<AdjNode>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void setEdgeCount(int edgeCount) {
        this.edgeCount = edgeCount;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }
}
