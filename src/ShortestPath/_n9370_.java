package ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _n9370_ {

    static List<List<Edge>> graph;
    static int INF = 1000 * 2000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()); // 교차로의 수
            int m = Integer.parseInt(st.nextToken()); // 도로의 수
            int t = Integer.parseInt(st.nextToken()); // 목적지 후보의 개수

            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int g = Integer.parseInt(st.nextToken()) - 1;
            int h = Integer.parseInt(st.nextToken()) - 1;

            // g 와 h 교차로 사이에 있는 도로를 지나갔다
            graph = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                graph.add(new ArrayList<>());
            }

            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int d = Integer.parseInt(st.nextToken());

                graph.get(a).add(new Edge(b, d));
                graph.get(b).add(new Edge(a, d));
            }

            List<Integer> targets = new ArrayList<>();
            for (int j = 0; j < t; j++) {
                int x = Integer.parseInt(br.readLine()) - 1;
                targets.add(x);
            }
            
            // 각 target 까지 이동하는 최단 경로 중 g - h 도로가 포함된 target 알아내기
            Collections.sort(targets);
            int[] distFromStart = dijkstra(s, n);
            int[] distFromG = dijkstra(g, n);
            int[] distFromH = dijkstra(h, n);
            for (int target : targets) {
                int d1 = distFromStart[target];
                int d2 = distFromStart[g] + distFromG[h] + distFromH[target];
                int d3 = distFromStart[h] + distFromH[g] + distFromG[target];

                if (d1 == d2 || d1 == d3) {
                    answer.append(target + 1).append(' ');
                }
            }
            answer.append('\n');
        }

        System.out.println(answer);
    }

    static int[] dijkstra(int start, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{start, dist[start]});

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();

            int now = temp[0];
            for (Edge next : graph.get(now)) {
                if (dist[now] + next.distance < dist[next.to]) {
                    dist[next.to] = dist[now] + next.distance;
                    pq.add(new int[]{next.to, dist[next.to]});
                }
            }
        }

        return dist;
    }

    static class Edge {
        int to;
        int distance;

        Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }
}