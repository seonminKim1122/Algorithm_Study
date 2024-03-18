package TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _n1005_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] times = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }

            int[] fanIn = new int[N];
            int[][] graph = new int[N][N];
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());

                int before = Integer.parseInt(st.nextToken()) - 1;
                int after = Integer.parseInt(st.nextToken()) - 1;

                graph[before][after] = 1;
                fanIn[after]++;
            }
            
            int W = Integer.parseInt(br.readLine()); // 지어야 하는 건물 번호

            int[] needTimes = new int[N];
            PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
            for (int i = 0; i < N; i++) {
                if (fanIn[i] == 0) {
                    needTimes[i] = times[i];
                    queue.add(new int[]{i, needTimes[i]});
                }
            }

            while (!queue.isEmpty()) {
                int[] temp = queue.poll();

                int now = temp[0];
                for (int next = 0; next < N; next++) {
                    if (graph[now][next] == 0) continue;
                    fanIn[next]--;

                    if (fanIn[next] == 0) {
                        needTimes[next] = needTimes[now] + times[next];
                        queue.add(new int[]{next, needTimes[next]});
                    }
                }
            }

            answer.append(needTimes[W - 1]).append('\n');
        }

        System.out.println(answer);
    }
}
