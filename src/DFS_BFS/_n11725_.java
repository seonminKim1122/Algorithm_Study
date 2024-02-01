package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _n11725_ {

    static int[] parent;
    static List<List<Integer>> graph;
    static boolean[] via;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        via = new boolean[N + 1];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dfs(1, 0);

        StringBuilder answer = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            answer.append(parent[i]).append('\n');
        }

        System.out.println(answer);
    }

    static void dfs(int node, int before) {
        via[node] = true;
        parent[node] = before;

        for (int next : graph.get(node)) {
            if (!via[next]) {
                dfs(next, node);
            }
        }
    }
}
/*
의사 코드
1. 인접 행렬을 이용해 문제에서 제시된 그래프를 만든다.
2. DFS 를 이용해 그래프를 탐색하고 특정 노드를 탐색할 때 이전 노드가 무엇인지에 대한 정보를 같이 넘겨
parent 배열에 저장
3. parent 배열의 내용을 출력

시간복잡도
-> O(N ^ N)
-> 인접행렬로 구현했기 때문에 N * N 사이즈 배열을 다 탐색해야 한다.

인접행렬로 구현 시 -> 메모리 초과 발생
문제에서 N 의 사이즈가 최대 100,000 -> N * N 이면 100,000 * 100,000 크기 배열에 int 자료형이 저장되므로
4byte * 10_000_000_000 이므로 메모리 초과가 발생하는 것.

따라서 인접리스트로 구현해야 한다!!
인접리스트로 구현하면 실제로 저장되는 건 간선의 개수만큼의 정보만 저장!

추가로 인접리스트를 사용했으니 시간복잡도도 O(N + E) 가 된다
 */
