import java.util.*;

public class BreadthFirstSearch<V> extends Search<V> {

    @Override
    public void search(WeightedGraph<V> graph, V startData) {
        initializeSearch(graph, startData);

        Vertex<V> start = graph.getVertex(startData);
        if (start == null) return;

        Queue<Vertex<V>> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        distance.put(start, 0.0);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    distance.put(neighbor, distance.get(current) + weight);
                    queue.add(neighbor);
                }
            }
        }
    }

    public List<V> getShortestPathByEdges(V targetData, WeightedGraph<V> graph) {
        Vertex<V> target = graph.getVertex(targetData);
        if (target == null || !visited.contains(target)) {
            return null;
        }

        List<V> path = new ArrayList<>();
        Vertex<V> current = target;

        while (current != null && parent.containsKey(current)) {
            path.add(0, current.getData());
            current = parent.get(current);
        }

        if (current != null) {
            path.add(0, current.getData());
        }

        return path;
    }
}
