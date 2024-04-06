package ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _n18114_ {

    static List<List<Edge>> graph = new ArrayList<>();
    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        // 시작점 to 도착점 = 시작점 to 건우 + 건우 to 도착점 인지 확인하기
        // 시작점에서 다익스트라 1번, 건우에서 다익스트라 1번
        int[] distance1 = dijkstra(1, V);
        int[] distance2 = dijkstra(P, V);

        if (distance1[V] == distance1[P] + distance2[V]) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }
    }

    static int[] dijkstra(int start, int V) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{start, dist[start]});

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();

            int now = temp[0];

            for (Edge edge : graph.get(now)) {
                int next = edge.to;

                if (dist[next] > dist[now] + edge.cost) {
                    dist[next] = dist[now] + edge.cost;
                    pq.add(new int[]{next, dist[next]});
                }
            }
        }

        return dist;
    }
    
    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
