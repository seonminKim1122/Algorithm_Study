package TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _n3665_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answers = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] teamsOrderByRank = new int[n];
            for (int i = 0; i < n; i++) { // 1등 팀부터 차례로 주어진다
                int team = Integer.parseInt(st.nextToken()) - 1;
                teamsOrderByRank[i] = team;
            }

            int[][] graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                int before = teamsOrderByRank[i];
                for (int j = i + 1; j < n; j++) {
                    int after = teamsOrderByRank[j];
                    graph[before][after] = 1;
                }
            }

            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                // a 와 b 의 상대적인 등수가 바뀜
                if (graph[a][b] == 1) {
                    graph[a][b] = 0;
                    graph[b][a] = 1;
                } else {
                    graph[b][a] = 0;
                    graph[a][b] = 1;
                }
            }

            int[] fanIn = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    fanIn[i] += graph[j][i];
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (fanIn[i] == 0) {
                    queue.add(i);
                }
            }

            StringBuilder answer = new StringBuilder();
            while (!queue.isEmpty()) {
                int now = queue.poll();
                answer.append(now + 1).append(' ');

                for (int next = 0; next < n; next++) {
                    if (graph[now][next] == 0) continue;

                    fanIn[next]--;
                    if (fanIn[next] == 0) {
                        queue.add(next);
                    }
                }
            }

            boolean isPossible = true;
            for (int i = 0; i < n; i++) {
                if (fanIn[i] != 0) {
                    isPossible = false;
                    break;
                }
            }

            if (!isPossible) {
                answers.append("IMPOSSIBLE").append('\n');
            } else {
                answers.append(answer).append('\n');
            }
        }

        System.out.println(answers);
    }
}

/*
등수
5 4 3 2 1

0 0 0 0 0
1 0 0 1 0
1 1 0 0 0
1 1 1 0 0
1 1 1 1 0


교체 (누가 누구보다 등수가 높아야 한다에 대한 정보가 바뀌는 것)
2 4
3 4

0 0 0 0 0
1 0 0 1 0
1 1 0 1 0
1 0 0 0 0
1 1 1 1 0

[4 2 1 3 0]
1등 - 5번
2등 - 3번
3등 - 2번
4등 - 4번
5등 - 1번
*/