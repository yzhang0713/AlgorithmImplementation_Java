import java.util.Objects;

public class Edge {

    private int edgeNodeOne;
    private int edgeNodeTwo;
    private int weight;

    public Edge(int edgeNodeOne, int edgeNodeTwo, int weight) {
        this.edgeNodeOne = edgeNodeOne;
        this.edgeNodeTwo = edgeNodeTwo;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return edgeNodeOne == edge.edgeNodeOne && edgeNodeTwo == edge.edgeNodeTwo && weight == edge.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(edgeNodeOne, edgeNodeTwo, weight);
    }

    public int getEdgeNodeOne() {
        return edgeNodeOne;
    }

    public void setEdgeNodeOne(int edgeNodeOne) {
        this.edgeNodeOne = edgeNodeOne;
    }

    public int getEdgeNodeTwo() {
        return edgeNodeTwo;
    }

    public void setEdgeNodeTwo(int edgeNodeTwo) {
        this.edgeNodeTwo = edgeNodeTwo;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
