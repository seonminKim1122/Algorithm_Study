package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _n1922_ {


    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        int M = Integer.parseInt(br.readLine());
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            Edge edge = new Edge(from, to, cost);
            edges.add(edge);
        }

        System.out.println(kruskal(edges));
    }

    static int kruskal(PriorityQueue<Edge> edges) {
        int result = 0;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();

            int from = edge.from;
            int to = edge.to;

            if (union(from, to)) {
                result += edge.cost;
            }
        }

        return result;
    }

    static boolean union(int a, int b) {
        int root1 = find(a);
        int root2 = find(b);

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
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
/*
의사코드
1. 모든 컴퓨터를 연결하는 비용을 최소로 한다 => MST 구하기
2. kruskal 알고리즘을 이용
3. 모든 컴퓨터를 연결할 수 없는 경우는 없다고 하였으므로 사용한 간선의 개수는 셀 필요 없다.
※ 간선 하나의 최대 비용은 10,000 원 이고 컴퓨터의 최대 수는 1000 이므로 최대 비용은 int 범위를 넘지 않는다.
 */