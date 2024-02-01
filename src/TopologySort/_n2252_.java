package TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _n2252_ {

    static List<List<Integer>> graph = new ArrayList<>();
    static StringBuilder answer = new StringBuilder();
    static int[] fanIn;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        fanIn = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            fanIn[b]++;
        }

        topologySort();
        System.out.println(answer);
    }

    static void topologySort() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (fanIn[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            answer.append(now).append(' ');

            for (int next : graph.get(now)) {
                fanIn[next]--;
                if (fanIn[next] == 0) {
                    queue.add(next);
                }
            }
        }
    }
}
/*
의사 코드
1. 1 3 과 같이 입력이 주어지면 1 번이 3 번 보다 먼저 처리해야 되는 일이라는 뜻이므로 1 -> 3 간선을 추가
2. 만들어진 방향 그래프에 대해 위상 정렬 알고리즘 적용
3. Queue 에서 값을 제거할 때 StringBuilder 에 추가하면 그게 곧 정답

시간 복잡도
O(N + E)
-> 인접리스트로 구현
 */