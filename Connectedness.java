//Aidar Sarvartdinov

import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ArrayList;

public class Connectedness {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (scanner.nextInt() == 1) {
                    graph.addEdge(graph.getVertex(i), graph.getVertex(j));
                }
            }
        }
        if (graph.answer()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

class Graph<V> {
    class Vertex {
        V value;
        List<Vertex> adjacent;
        int indgree;
        int outdgree;

        public Vertex(V value) {
            this.value = value;
            this.adjacent = new LinkedList<>();
            this.indgree = 0;
            this.outdgree = 0;
        }
    }

    class Edge {
        Vertex from;
        Vertex to;

        public Edge(Vertex from, Vertex to) {
            this.from = from;
            this.to = to;
        }
    }
    List<Vertex> vertices;
    List<Edge> edges;

    public Graph() {
        this.vertices = new LinkedList<>();
        this.edges = new LinkedList<>();
    }

    public boolean answer() {
        for (Vertex vertex: vertices) {
            if (!AidarSarvartdinov_bfs(vertex)) {
                return false;
            }
        }
        return true;
    }

    public boolean AidarSarvartdinov_bfs(Vertex vertex) {
        ArrayList<Vertex> nodes = new ArrayList<>();
        ArrayList<Vertex> passed = new ArrayList<>();
        passed.add(vertex);
        nodes.add(vertex);
        while (!nodes.isEmpty()) {
            Vertex node = nodes.remove(0);
            for (Vertex adj: node.adjacent) {
                if (!passed.contains(adj)) {
                    passed.add(adj);
                    nodes.add(adj);
                }
            }
        }
        return passed.size() == vertices.size();
    }

    public Vertex addVertex(V value) {
        Vertex v = new Vertex(value);
        this.vertices.add(v);
        return v;
    }

    public Vertex getVertex(V value) {
        for (Vertex vertex: vertices) {
            if (vertex.value == value) {
                return vertex;
            }
        }
        return null;
    }

    public Edge addEdge(Vertex from, Vertex to) {
        Edge edge = new Edge(from, to);
        this.edges.add(edge);
        from.adjacent.add(to);
        from.outdgree++;
        to.indgree++;
        return edge;
    }

}