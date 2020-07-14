import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class GraphDS {

    abstract static class VertexMap {
        private String location;
        private List<Vertex> neighbors;

        abstract public String getLocation();

        abstract public void setLocation(String loc);

        abstract public List<Vertex> getNeighbors();

        abstract public Edge createEdge(Vertex v);
    }

    abstract static class EdgeMap {
        private Vertex source;
        private Vertex dest;

        abstract public Vertex getSource();

        abstract public Vertex getDest();

        abstract public boolean equals(Vertex u, Vertex v);
    }

    abstract static class GraphMap {
        private List<Vertex> vertices;
        private Map<Edge, Integer> weights;

        abstract public List<Vertex> getVertices();

        abstract public int getWeight(Vertex u, Vertex v);

        abstract public Graph setWeight(Edge e, int weight);

        abstract public void dfs(Vertex source);

        abstract public void bfs(Vertex source);

        abstract public void topologicalSort(boolean detectCycle);

        abstract public void dijkstra(Vertex source, Vertex dest);

        abstract public void astar(Vertex source, Vertex dest);
    }

    static class Vertex extends VertexMap {
        private String location;
        private List<Vertex> neighbors;

        Vertex(String loc) {
            location = loc;
            neighbors = new ArrayList<>();
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String loc) {
            location = loc.length() < 0 ? location : loc;
        }

        public List<Vertex> getNeighbors() {
            return neighbors;
        }

        public Edge createEdge(Vertex v) {
            neighbors.add(v);
            return new Edge(this, v);
        }
    }

    static class Edge extends EdgeMap {
        private Vertex source;
        private Vertex dest;

        Edge(Vertex s, Vertex d) {
            source = s;
            dest = d;
        }

        public Vertex getSource() {
            return source;
        }

        public Vertex getDest() {
            return dest;
        }

        public boolean equals(Vertex u, Vertex v) {
            return source.location == u.location && dest.location == v.location;
        }
    }

    static class Graph extends GraphMap {
        private List<Vertex> vertices;
        private Map<Edge, Integer> weights;

        Graph() {
            vertices = new ArrayList<>();
            weights = new HashMap<>();
        }

        Graph(List<Vertex> vs) {
            vertices = vs;
            weights = new HashMap<>();
        }

        Graph(Vertex[] vs) {
            vertices = new ArrayList<>();
            for (Vertex v : vs) {
                vertices.add(v);
            }
            weights = new HashMap<>();
        }

        public List<Vertex> getVertices() {
            return vertices;
        }

        public int getWeight(Vertex u, Vertex v) {
            for (Map.Entry<Edge, Integer> entry : weights.entrySet()) {
                Edge e = entry.getKey();
                if (e.equals(u, v)) {
                    return entry.getValue();
                }
            }
            return -1;
        }

        private void visit(Vertex v) {
            System.out.println(String.format("Visiting: %s", v.location));
        }

        public void dfs(Vertex source) {
            System.out.println("dfs()");
            Stack<Vertex> frontier = new Stack<>();
            Set<Vertex> visited = new HashSet<>();

            frontier.push(source);

            while (!frontier.isEmpty()) {
                Vertex curr = frontier.pop();
                if (!visited.contains(curr)) {
                    visited.add(curr);
                    visit(curr);
                    for (Vertex n : curr.getNeighbors()) {
                        frontier.add(n);
                    }
                }
            }
        }

        public void bfs(Vertex source) {
            System.out.println("bfs()");
            Map<String, String> parentMap = new HashMap<>();
            Map<String, Integer> levelMap = new HashMap<>();
            Queue<Vertex> frontier = new LinkedList<>();
            Queue<Vertex> nextFrontier;
            int currLevel = 0;

            // No parent, if this graph was a tree (all trees are graphs
            // but not all graphs are trees) the source would be the root node
            parentMap.put(source.location, null);
            levelMap.put(source.location, currLevel);
            frontier.add(source);

            while (!frontier.isEmpty()) {
                nextFrontier = new LinkedList<>();
                for (Vertex v : frontier) {
                    for (Vertex n : v.getNeighbors()) {
                        if (!levelMap.containsKey(n)) {
                            levelMap.put(n.location, currLevel);
                            parentMap.put(n.location, v.location);
                            nextFrontier.add(n);
                        }
                    }
                }
                currLevel += 1;
                frontier = nextFrontier;
            }

            System.out.println(String.format("levelMap: %s", levelMap.toString()));
            System.out.println(String.format("parentMap: %s", parentMap.toString()));
        }

        private void tsUtil(Vertex curr, Stack<Vertex> stack, Map<Vertex, Boolean> cycleDetector) {
            if (cycleDetector != null) {
                if (cycleDetector.containsKey(curr) && cycleDetector.get(curr)) {
                    System.out.println("Cycle Detected! Not a DAG");
                    return;
                }
            }
            if (stack.contains(curr)) {
                return;
            }

            if (cycleDetector != null) {
                cycleDetector.put(curr, true);
            }

            for (Vertex n : curr.getNeighbors()) {
                tsUtil(n, stack, cycleDetector);
            }

            if (cycleDetector != null) {
                cycleDetector.put(curr, false);
            }

            stack.add(curr);
        }

        public void topologicalSort(boolean detectCycle) {
            System.out.println("tsort()");
            Map<Vertex, Boolean> cycleDetector = detectCycle ? new HashMap<>() : null;
            Stack<Vertex> stack = new Stack<>();

            for (Vertex v : vertices) {
                tsUtil(v, stack, cycleDetector);
            }

            Queue<Vertex> sortedGraph = new LinkedList<Vertex>();

            while (!stack.isEmpty()) {
                sortedGraph.add(stack.pop());
            }

            System.out.println("Sorted Graph");
            while (!sortedGraph.isEmpty()) {
                visit(sortedGraph.remove());
            }
        }

        private Comparator<Vertex> distMapComparator(Map<Vertex, Integer> distMap) {
            return new Comparator<Vertex>() {
                @Override
                public int compare(Vertex a, Vertex b) {
                    if (distMap.get(a) == distMap.get(b)) {
                        return 0;
                    }
                    return distMap.get(a) < distMap.get(b) ? -1 : 1;
                }
            };
        }

        private void printPath(Map<Vertex, Vertex> parentMap, Vertex dest) {
            Stack<Vertex> pathStack = new Stack<>();
            Vertex curr = dest;
            while (curr != null) {
                pathStack.push(curr);
                curr = parentMap.get(curr);
            }
            System.out.println("Shortest Path:");
            while (!pathStack.isEmpty()) {
                visit(pathStack.pop());
            }
        }

        public void dijkstra(Vertex source, Vertex dest) {
            System.out.println("dijkstra()");
            if (source == dest) {
                System.out.println("Source == Dest");
                return;
            }

            Map<Vertex, Integer> distMap = new HashMap<>();
            Map<Vertex, Vertex> parentMap = new HashMap<>();
            PriorityQueue<Vertex> frontier = new PriorityQueue<>(distMapComparator(distMap));

            for (Vertex v : vertices) {
                distMap.put(v, Integer.MAX_VALUE);
                parentMap.put(v, null);
                frontier.add(v);
            }

            distMap.put(source, 0);

            while (!frontier.isEmpty()) {
                Vertex u = frontier.poll();
                for (Vertex v : u.getNeighbors()) {
                    int newDist = distMap.get(u) + getWeight(u, v);
                    if (newDist < distMap.get(v)) {
                        distMap.put(v, newDist);
                        parentMap.put(v, u);
                    }
                }
            }

            if (!parentMap.containsKey(dest) && dest != source) {
                System.out.println("Could not find shortest path.");
                return;
            }

            printPath(parentMap, dest);
        }

        private int mdist(Map<Vertex, Integer> distMap, Vertex currNeighbor) {
            // Manhattan Distance Heuristic
            int h = Integer.MAX_VALUE;
            for (Vertex v : vertices) {
                h = Math.min(h, distMap.get(v) + getWeight(v, currNeighbor));
            }
            return h;
        }

        public void astar(Vertex source, Vertex dest) {
            System.out.println("astar()");
            if (source == dest) {
                System.out.println("Source == Dest");
                return;
            }

            Map<Vertex, Integer> gScore = new HashMap<>();
            Map<Vertex, Integer> fScore = new HashMap<>();
            Map<Vertex, Vertex> parentMap = new HashMap<>();
            PriorityQueue<Vertex> frontier = new PriorityQueue(distMapComparator(fScore));

            for (Vertex v : vertices) {
                gScore.put(v, Integer.MAX_VALUE);
                fScore.put(v, Integer.MAX_VALUE);
                parentMap.put(v, null);
                frontier.add(v);
            }

            gScore.put(source, 0);
            fScore.put(source, mdist(gScore, source));

            while (!frontier.isEmpty()) {
                Vertex curr = frontier.poll();
                if (curr == dest) {
                    printPath(parentMap, curr);
                    break;
                }
                for (Vertex n : curr.getNeighbors()) {
                    int newGScore = gScore.get(curr) + getWeight(curr, n);
                    if (newGScore < gScore.get(n)) {
                        gScore.put(n, newGScore);
                        fScore.put(n, gScore.get(n) + mdist(gScore, n));
                        parentMap.put(n, curr);
                        if (!frontier.contains(n)) {
                            frontier.add(n);
                        }
                    }
                }
            }
        }

        public Graph setWeight(Edge e, int weight) {
            this.weights.put(e, weight);
            return this;
        }

    }

    public static void main(String[] args) {
        Vertex a = new Vertex("House A");
        Vertex b = new Vertex("House B");
        Vertex c = new Vertex("House C");
        Vertex d = new Vertex("Building D");
        Vertex e = new Vertex("Building E");
        Vertex f = new Vertex("Store F");
        Vertex g = new Vertex("Store G");
        Vertex h = new Vertex("Store H");
        Vertex i = new Vertex("Park I");

        Vertex[] vertices = new Vertex[] { a, b, c, d, e, f, g, h, i };

        Graph locationGraph = new Graph(vertices);

        locationGraph.setWeight(a.createEdge(b), 15).setWeight(a.createEdge(c), 25).setWeight(b.createEdge(h), 5)
                .setWeight(b.createEdge(i), 25).setWeight(b.createEdge(e), 10).setWeight(h.createEdge(i), 15)
                .setWeight(c.createEdge(d), 10).setWeight(c.createEdge(e), 20).setWeight(e.createEdge(f), 10)
                .setWeight(e.createEdge(g), 5);

        locationGraph.dfs(c);
        locationGraph.bfs(a);
        locationGraph.dijkstra(a, g);
        locationGraph.astar(a, f);
        locationGraph.topologicalSort(false);
    }
}