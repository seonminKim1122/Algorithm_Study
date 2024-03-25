package ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _n4485_ {

    static final int INF = 126 * 126 * 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 1;
        StringBuilder answer = new StringBuilder();
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            answer.append("Problem ").append(T).append(": ");

            int[][] cave = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            // (0, 0) 에서 (N - 1, N - 1) 까지 가는 최소 비용
            int[][] cost = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(cost[i], INF);
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
            cost[0][0] = cave[0][0];
            pq.add(new int[]{cost[0][0], 0, 0});

            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};
            while (!pq.isEmpty()) {
                int[] now = pq.poll();

                int x = now[1];
                int y = now[2];

                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (cost[nx][ny] <= cost[x][y] + cave[nx][ny]) continue;

                    cost[nx][ny] = cost[x][y] + cave[nx][ny];
                    pq.add(new int[]{cost[nx][ny], nx, ny});
                }
            }

            answer.append(cost[N - 1][N - 1]).append("\n");
            T++;
        }

        System.out.println(answer);
    }
}