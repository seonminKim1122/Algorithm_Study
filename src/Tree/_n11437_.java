package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _n11437_ {

    public static void main(String[] args) throws IOException {
        // 데이터 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 노드의 개수
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            tree.get(node1).add(node2);
            tree.get(node2).add(node1);
        }

        // parent, depth 정보 만들기
        int[] parent = new int[N + 1];
        int[] depth = new int[N + 1];

        Queue<int[]> queue = new LinkedList<>();
        parent[1] = 0;
        depth[1] = 1;
        queue.add(new int[]{1, depth[1]});

        while (!queue.isEmpty()) {
            int[] info = queue.poll();

            int now = info[0];
            int d = info[1];

            for (int next : tree.get(now)) {
                if (depth[next] != 0) continue;
                parent[next] = now;
                depth[next] = d + 1;
                queue.add(new int[]{next, depth[next]});
            }
        }

        // M 줄 입력받고 각 케이스의 LCA 찾기
        int M = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            answer.append(findLCA(node1, node2, parent, depth));
            answer.append('\n');
        }

        System.out.println(answer);
    }

    static int findLCA(int node1, int node2, int[] parent, int[] depth) {
        int depth1 = depth[node1];
        int depth2 = depth[node2];

        while (depth1 > depth2) {
            node1 = parent[node1];
            depth1 = depth[node1];
        }

        while (depth1 < depth2) {
            node2 = parent[node2];
            depth2 = depth[node2];
        }

        while (node1 != node2) {
            node1 = parent[node1];
            node2 = parent[node2];
        }

        return node1;
    }
}
