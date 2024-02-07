package TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _n2623 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N][N];
        int[] fanIn = new int[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int K = Integer.parseInt(st.nextToken());

            int from = Integer.parseInt(st.nextToken()) - 1;
            for (int j = 0; j < K - 1; j++) {
                int to = Integer.parseInt(st.nextToken()) - 1;
                if (graph[from][to] == 0) {
                    graph[from][to] = 1;
                    fanIn[to]++;
                }
                from = to;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (fanIn[i] == 0) {
                queue.add(i);
                cnt++;
            }
        }

        StringBuilder answer = new StringBuilder();

        while (!queue.isEmpty()) {
            int now = queue.poll();
            answer.append(now + 1).append('\n');

            for (int next = 0; next < N; next++) {
                if (graph[now][next] == 1) {
                    graph[now][next] = 0;
                    fanIn[next]--;
                    if (fanIn[next] == 0) {
                        queue.add(next);
                        cnt++;
                    }
                }
            }
        }

        System.out.println(cnt == N ? answer : 0);
    }
}
/*
의사코드
1. 위상 정렬 알고리즘을 통해 일의 순서를 구한다
2. 만약 사이클이 존재하면 순서를 정하는 것이 불가능하므로 0을 출력한다
 */
