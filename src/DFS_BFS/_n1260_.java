package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _n1260_ {

    static int[][] graph;
    static boolean[] visited;

    static StringBuilder dfsAnswer = new StringBuilder();
    static StringBuilder bfsAnswer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());


        graph = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A][B] = 1;
            graph[B][A] = 1;
        }

        visited = new boolean[N + 1];
        dfs(V);

        visited = new boolean[N + 1];
        bfs(V);

        System.out.println(dfsAnswer);
        System.out.println(bfsAnswer);
    }

    static void dfs(int node) {
        visited[node] = true;
        dfsAnswer.append(node).append(' ');

        for (int i = 0; i < graph[node].length; i++) {
            if (graph[node][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }

    static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;
        bfsAnswer.append(node).append(' ');

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0; i < graph[now].length; i++) {
                if (graph[now][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                    bfsAnswer.append(i).append(' ');
                }
            }
        }
    }
}
