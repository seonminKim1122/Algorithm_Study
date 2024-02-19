package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _n1647_ {

    static int[] parent;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> edges = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            Edge edge = new Edge(A, B, C);
            edges.add(edge);
        }

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        System.out.println(kruskal(edges));
    }

    static int kruskal(PriorityQueue<Edge> edges) {
        int cnt = 0;
        int result = 0;
        int max = 0;
        while (cnt < N - 1 && !edges.isEmpty()) {
            Edge edge = edges.poll();

            if (union(edge.from, edge.to)) {
                cnt++;
                result += edge.cost;
                max = Math.max(max, edge.cost);
            }
        }

        return result - max;
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

    static class Edge {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
