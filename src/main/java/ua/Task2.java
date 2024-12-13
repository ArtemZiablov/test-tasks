package ua;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task2 {
    static class Edge {
        int to;
        int cost;
        public Edge(int t, int c) {
            to = t; cost = c;
        }
    }

    static final int INF = 1000000000; // a large number for initializing distances

    // Function to run Dijkstra's algorithm
    // start, end - city indices (from 1 to n)
    // graph — adjacency list
    public static int dijkstra(int start, int end, List<Edge>[] graph) {
        int n = graph.length - 1;
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start,0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            int d = cur[1];

            if (d > dist[u]) continue; // already found a shorter path

            if (u == end) {
                return dist[u]; // can stop if we reached the destination
            }

            for (Edge e: graph[u]) {
                int v = e.to;
                int nd = d + e.cost;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.add(new int[]{v, nd});
                }
            }
        }

        return dist[end];
    }

    public static void main(String[] args) throws IOException {
        // Reading input data
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine().trim()); // number of test cases
        for (int test = 0; test < s; test++) {
            int n = Integer.parseInt(br.readLine().trim());

            // Mapping: city name -> index
            Map<String, Integer> nameToIndex = new HashMap<>();

            // Adjacency list: graph of n cities (1-based indexing)
            List<Edge>[] graph = new List[n+1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            // Reading city data
            for (int i = 1; i <= n; i++) {
                String cityName = br.readLine().trim();
                nameToIndex.put(cityName, i);

                int p = Integer.parseInt(br.readLine().trim());
                for (int j = 0; j < p; j++) {
                    String[] parts = br.readLine().trim().split(" ");
                    int nr = Integer.parseInt(parts[0]); // index of the neighboring city
                    int cost = Integer.parseInt(parts[1]); // transportation cost
                    // Add an edge to the graph
                    graph[i].add(new Edge(nr, cost));
                }
            }

            // Reading the number of queries
            int r = Integer.parseInt(br.readLine().trim());
            for (int q = 0; q < r; q++) {
                String[] cities = br.readLine().trim().split(" ");
                String startName = cities[0];
                String endName = cities[1];

                int startIndex = nameToIndex.get(startName);
                int endIndex = nameToIndex.get(endName);

                // Run Dijkstra to find the shortest path
                int answer = dijkstra(startIndex, endIndex, graph);
                System.out.println(answer);
            }

            // There might be an empty line between tests
            if (test < s-1) {
                br.readLine();
            }
        }
    }
}

/*
input:
1
4
gdansk
2
2 1
3 3
bydgoszcz
3
1 1
3 1
4 4
torun
3
1 3
2 1
4 1
warszawa
2
2 4
3 1
2
gdansk warszawa
bydgoszcz warszawa
*/
