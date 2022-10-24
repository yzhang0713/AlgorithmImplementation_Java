import java.util.Objects;

public class AdjNode {
    private int endNode;
    private int weight;

    public AdjNode(int endNode, int weight) {
        this.endNode = endNode;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdjNode)) return false;
        AdjNode adjNode = (AdjNode) o;
        return endNode == adjNode.endNode && weight == adjNode.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(endNode, weight);
    }

    public int getEndNode() {
        return endNode;
    }

    public void setEndNode(int endNode) {
        this.endNode = endNode;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
