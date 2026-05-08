import java.util.*;

public class DijkstraSearch<V> extends Search<V> {

    @Override
    public void search(WeightedGraph<V> graph, V startData) {
        initializeSearch(graph, startData);

        Vertex<V> start = graph.getVertex(startData);
        if (start == null) return;

        PriorityQueue<VertexDistance<V>> pq = new PriorityQueue<>(Comparator.comparingDouble(vd -> vd.distance));
        pq.add(new VertexDistance<>(start, 0.0));
        distance.put(start, 0.0);

        while (!pq.isEmpty()) {
            VertexDistance<V> currentVD = pq.poll();
            Vertex<V> current = currentVD.vertex;

            if (currentVD.distance > distance.get(current)) {
                continue;
            }

            visited.add(current);

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();
                double newDist = distance.get(current) + weight;

                if (newDist < distance.get(neighbor)) {
                    distance.put(neighbor, newDist);
                    parent.put(neighbor, current);
                    pq.add(new VertexDistance<>(neighbor, newDist));
                }
            }
        }
    }

    public List<V> getShortestPathByWeight(V targetData, WeightedGraph<V> graph) {
        Vertex<V> target = graph.getVertex(targetData);
        if (target == null || distance.get(target) == Double.POSITIVE_INFINITY) {
            return null;
        }

        List<V> path = new ArrayList<>();
        Vertex<V> current = target;

        while (current != null) {
            path.add(0, current.getData());
            current = parent.get(current);
        }

        return path;
    }

    private static class VertexDistance<V> {
        Vertex<V> vertex;
        double distance;

        VertexDistance(Vertex<V> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}
