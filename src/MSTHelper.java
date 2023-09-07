/**
 * This is the helper class to detect the two relationships of an edge in a graph data structure (https://github.com/yzhang0713/AlgorithmImplementation_Java)
 *      1. Whether the edge is in any Minimum Spanning Tree
 *      2. Whether the edge is in all Minimum Spanning Tree
 */
public class MSTHelper {

    /*
    Discussion about Issue 1:
        1. (a) Used recursion to conduct DFS on the graph node, with a boolean array serving as the memoization table
           (b) Each recursion, will try to find a path that satisfy the condition as discussed in cycle property of graph
               The boolean array is used to keep note on the nodes that have been visited before, to avoid duplicated work
        2. Base case: If weight to current node is larger than OR EQUAL TO target edge weight, then do not need to consider
                      If current node is the second edge node we are looking for, then we have found the path
           Recursion: For every round, will check all the adjacent nodes of current node.
                      For each adjacent node, do base case check, and skip or return accordingly.
                      Check visited node record first, if this node has been recorded, then it must cannot reach the other node, can skip
                      Otherwise, recursively search all adjacent node of current node
        3. Need to return true or false, indicating whether the edge must be in some MST
        4. The boolean array recording whether node is visited will be flipped to true every time we visited a node in graph
        5. In each round, the maximum number of subproblem equals to the number of adjacent node, which can be as many as V
        6. With the boolean array, maximum number of subproblem overall will be V
        7. Within each subproblem, we need to loop through all adjacent nodes, counting that maximum subproblem being V, we have total number of O(|V| + |E|)
     */

    /**
     * This method uses the cycle property of the graph to prove whether an edge belongs to any MST
     * @param edge Edge
     * @param graph Graph
     * @return boolean
     */
    public static boolean isEdgeInAnyMST(Edge edge, Graph graph) {
        boolean[] visitedNodes = new boolean[graph.getNodeCount()];
        for (int i = 0; i < graph.getNodeCount(); i++) {
            visitedNodes[i] = false;
        }
        // Will call hasSmallerWeightPathBetweenNodes to check if such path exist, if so, then edge does not belong to any MST
        return !hasSmallerWeightPathBetweenNodes(edge.getEdgeNodeOne(), edge.getEdgeNodeTwo(), edge.getWeight(), graph, visitedNodes);
    }

    private static boolean hasSmallerWeightPathBetweenNodes(int nodeOne, int nodeTwo, int edgeWeight, Graph graph, boolean[] visitedNodes) {
        if (visitedNodes[nodeOne]) {
            return false;
        }
        for (AdjNode adjNode : graph.getAdjacencyList().get(nodeOne)) {
            if (adjNode.getWeight() >= edgeWeight) {
                continue;
            }
            int currentNode = adjNode.getEndNode();
            if (currentNode == nodeTwo) {
                // Found path with smaller weight to nodeTwo, need to return true here
                return true;
            }
            // Mark current node as visited
            visitedNodes[currentNode] = true;
            boolean hasPathFromHere = hasSmallerWeightPathBetweenNodes(currentNode, nodeTwo, edgeWeight, graph, visitedNodes);
            // In case we have found a smaller path, will return true
            if (hasPathFromHere) {
                return true;
            }
            // Otherwise, current node is done, we can move on next adjacent node
        }
        // At this point, all node has been searched and no good path, so return false
        return false;
    }

    /*
    Discussion about Issue 1:
        Logic: In order for an edge to belong to all MSTs, it must satisfy the cut property.
               The cut property says that if one edge is the minimum weight in any of the cut-set, then it must be in all MST.
               So, we want to find out if there exists such cut-set, such that target edge has the minimum weight in it.
               In order to do that, we can execute the following steps:
                    -> Notice that the final cut-set (if there is any) must separate the two end nodes of edge. So we start
                       by putting two end nodes on two sets.
                    -> By doing this, we will have one set of nodes containing only one edge node, the other set containing all rest nodes.
                    -> Next, we conduct something similar to prims algorithm
                    -> For every iteration, we will try to find one connecting edge that has weight smaller or equal to target edge
                    -> If we cannot find such edge, we have found the cut-set, the search is done
                    -> Otherwise, we add new node into first node set, and reconstructing connecting edges
                    -> If we find the other node without using target edge, then target edge does not belong to connecting edge anymore, we can stop
        Note: Even though the logic is different, the way we are building the two node sets will have almost identical code as previous problem.
        1. (a) Used recursion to conduct DFS on the graph node, with a boolean array serving as the memoization table
           (b) Each recursion, will try to find a path that satisfy the condition as discussed in cut property of graph
               The boolean array is used to keep note on the nodes that have been visited before, to avoid duplicated work
        2. Base case: If weight to current node is larger than target edge weight, then do not need to consider
                      If current node is the second edge node we are looking for, then we have found the path
           Recursion: For every round, will check all the adjacent nodes of current node.
                      For each adjacent node, do base case check, and skip or return accordingly.
                      Check visited node record first, if this node has been recorded, then it must cannot reach the other node, can skip
                      Otherwise, recursively search all adjacent node of current node
        3. Need to return true or false, indicating whether the edge must be in some MST
        4. The boolean array recording whether node is visited will be flipped to true every time we visited a node in graph
        5. In each round, the maximum number of subproblem equals to the number of adjacent node, which can be as many as V
        6. With the boolean array, maximum number of subproblem overall will be V
        7. Within each subproblem, we need to loop through all adjacent nodes, counting that maximum subproblem being V, we have total number of O(|V| + |E|)
     */

    /**
     * This method uses the cut property of the graph to prove whether an edge belongs to all MST
     * @param edge Edge
     * @param graph Graph
     * @return boolean
     */
    public static boolean isEdgeInAllMST(Edge edge, Graph graph) {
        boolean[] visitedNodes = new boolean[graph.getNodeCount()];
        for (int i = 0; i < graph.getNodeCount(); i++) {
            visitedNodes[i] = false;
        }
        // Will call hasSmallerOrEqualWeightPathBetweenNodes to check if such path exist, if so, then edge does not belong to all MST
        return !hasSmallerOrEqualWeightPathBetweenNodes(edge.getEdgeNodeOne(), edge.getEdgeNodeTwo(), edge.getWeight(), graph, visitedNodes);
    }

    private static boolean hasSmallerOrEqualWeightPathBetweenNodes(int nodeOne, int nodeTwo, int edgeWeight, Graph graph, boolean[] visitedNodes) {
        if (visitedNodes[nodeOne]) {
            return false;
        }
        for (AdjNode adjNode : graph.getAdjacencyList().get(nodeOne)) {
            if (adjNode.getWeight() > edgeWeight) {
                continue;
            }
            int currentNode = adjNode.getEndNode();
            if (currentNode == nodeTwo) {
                // Found path with smaller weight to nodeTwo, need to return true here
                return true;
            }
            // Mark current node as visited
            visitedNodes[currentNode] = true;
            boolean hasPathFromHere = hasSmallerOrEqualWeightPathBetweenNodes(currentNode, nodeTwo, edgeWeight, graph, visitedNodes);
            // In case we have found a smaller or equal path, will return true
            if (hasPathFromHere) {
                return true;
            }
            // Otherwise, current node is done, we can move on next adjacent node
        }
        // At this point, all node has been searched and no good path, so return false
        return false;
    }
}
