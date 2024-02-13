package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _n1197_ {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());


        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            Edge edge = new Edge(A, B, C);
            pq.add(edge);
        }

        System.out.println(kruskal(pq, V));
    }

    static int kruskal(PriorityQueue<Edge> pq, int V) {
        int result = 0;
        int used = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (union(now.from, now.to)) {
                used += 1;
                result += now.weight;
            }
        }

        if (used == V - 1) return result;
        return Integer.MAX_VALUE;
    }

    static boolean union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 == root2) return false;

        parent[root2] = root1;
        return true;
    }

    static int find(int node) {
        if (parent[node] == node) return node;
        parent[node] = find(parent[node]);
        return parent[node];
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        Edge (int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
