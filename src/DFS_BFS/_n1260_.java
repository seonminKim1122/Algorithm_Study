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
/*
의사코드
1. DFS 와 BFS 를 수행할 메서드 만들고 임의의 노드를 지날 때마다 각 방법의 StringBuilder 에 추가
2. 마지막에 DFS -> BFS 순으로 출력
3. 다만 이동할 수 있는 노드가 여러 개 일때 번호가 빠른 것부터 탐색해야 하므로 인접리스트보다는 인접행렬로 구현

시간복잡도
-> DFS, BFS 와 같은 탐색 알고리즘은 문제에서 탐색을 요구한 크기가 결정
-> O(N * N) -> 만약 인접 행렬의 크기가 아니라 인접 리스트로 구현하면 간선의 개수가 시간복잡도 결정
 */