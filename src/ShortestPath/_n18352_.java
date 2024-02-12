package ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _n18352_ {

    static int INF = 987654321;
    static int[] dist;
    static List<List<Edge>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, 1));
        }

        dist = new int[N + 1];
        dijkstra(X);

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                answer.append(i).append('\n');
            }
        }

        System.out.println(answer.length() == 0 ? -1 : answer);
    }

    static void dijkstra(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{start, dist[start]});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            int from = now[0];

            List<Edge> edges = graph.get(from);

            for (Edge edge : edges) {
                int to = edge.to;
                int value = edge.dist;

                if (dist[to] > dist[from] + value) {
                    dist[to] = dist[from] + value;
                    pq.add(new int[]{to, dist[to]});
                }
            }
        }
    }

    static class Edge {
        int to;
        int dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
}

/*
의사코드
1. X 를 출발지로 설정해서 다익스트라 알고리즘을 적용한다
-> 문제에서 모든 간선의 길이는 1이라고 했으므로 간선이 양수임이 보장된다.
2. 다익스트라 알고리즘을 통해 얻은 dist 배열에서 값이 K 인 노드 번호를 찾아 오름차순으로
한 줄에 하나씩 출력한다

시간복잡도
O(NlogN)
-> 우선순위 큐를 사용하면 최단 거리 노드를 찾는데 logN 의 시간이 소요
 */
