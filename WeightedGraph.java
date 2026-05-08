import java.util.*;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices;

    public WeightedGraph() {
        this.vertices = new HashMap<>();
    }

    public void addVertex(V data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public void addEdge(V sourceData, V destData, double weight) {
        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest = vertices.get(destData);

        if (source == null || dest == null) {
            throw new IllegalArgumentException("Source or destination vertex not found");
        }

        source.addAdjacentVertex(dest, weight);
        // For undirected graph, uncomment the line below:
        // dest.addAdjacentVertex(source, weight);
    }

    public void addUndirectedEdge(V sourceData, V destData, double weight) {
        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest = vertices.get(destData);

        if (source == null || dest == null) {
            throw new IllegalArgumentException("Source or destination vertex not found");
        }

        source.addAdjacentVertex(dest, weight);
        dest.addAdjacentVertex(source, weight);
    }

    public Collection<Vertex<V>> getAllVertices() {
        return vertices.values();
    }

    public boolean hasVertex(V data) {
        return vertices.containsKey(data);
    }

    public void removeVertex(V data) {
        Vertex<V> toRemove = vertices.get(data);
        if (toRemove == null) return;

        // Remove all edges pointing to this vertex
        for (Vertex<V> vertex : vertices.values()) {
            vertex.removeAdjacentVertex(toRemove);
        }

        vertices.remove(data);
    }

    public void removeEdge(V sourceData, V destData) {
        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest = vertices.get(destData);

        if (source != null && dest != null) {
            source.removeAdjacentVertex(dest);
        }
    }

    public int getVertexCount() {
        return vertices.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<V> vertex : vertices.values()) {
            sb.append(vertex).append(" -> ");
            for (Map.Entry<Vertex<V>, Double> entry : vertex.getAdjacentVertices().entrySet()) {
                sb.append(entry.getKey()).append("(").append(entry.getValue()).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
