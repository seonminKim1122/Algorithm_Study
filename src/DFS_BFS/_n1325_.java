package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _n1325_ {

    static List<List<Integer>> graph = new ArrayList<>();
    static int[] hacks;
    static int maxHack = 0;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        hacks = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
        }

//        for (int i = 1; i <= N; i++) {
//            dfs(i, new boolean[N + 1]);
//        }
        for (int i = 1; i <= N; i++) {
            bfs(i);
        }
        for (int i = 1; i <= N; i++) {
            if (maxHack < hacks[i]) {
                maxHack = hacks[i];
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (maxHack == hacks[i]) answer.append(i).append(' ');
        }

        System.out.println(answer);
    }

    static void dfs(int node, boolean[] via) {
        via[node] = true;
        hacks[node]++;

        List<Integer> nexts = graph.get(node);
        for (int next : nexts) {
            if (!via[next]) {
                dfs(next, via);
            }
        }
    }

    static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] via = new boolean[N + 1];
        queue.add(node);
        via[node] = true;
        hacks[node]++;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : graph.get(now)) {
                if (!via[next]) {
                    via[next] = true;
                    queue.add(next);
                    hacks[next]++;
                }
            }
        }
    }
}
/*
BFS 는 DFS 는 결국 모든 노드에 대해서 탐색을 수행한다는 점에서 시간복잡도가 동일할텐데
어떤 차이가 있는 걸까?

어디서 시간을 줄여야 하지?
 */