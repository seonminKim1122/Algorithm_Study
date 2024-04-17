package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class _n1707_ {

    static boolean isBipartite;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            isBipartite = true;
            int[] group = new int[V + 1];
            Arrays.fill(group, -1);

            for (int i = 1; i <= V; i++) {
                if (group[i] == -1) {
                    solve(graph, i, group, 0);
                }
            }
            answer.append(isBipartite ? "YES" : "NO").append("\n");
        }

        System.out.println(answer);
    }

    static void solve(List<List<Integer>> graph, int now, int[] group, int groupNumber) {
        if (!isBipartite) return;

        group[now] = groupNumber;
        groupNumber = (groupNumber + 1) % 2;

        for (int next : graph.get(now)) {
            if (group[next] != -1) {
                if (group[next] == group[now]) {
                    isBipartite = false;
                    return;
                }
            } else {
                solve(graph, next, group, groupNumber);
            }
        }
    }
}
