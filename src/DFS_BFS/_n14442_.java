package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _n14442_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int[][] cost = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cost[i], K + 1);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1});
        cost[0][0] = 0;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();

            int x = temp[0];
            int y = temp[1];
            int dist = temp[2];

            if (x == N - 1 && y == M - 1) {
                System.out.println(dist);
                return;
            }

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (map[nx][ny] == 1) {
                    if (cost[nx][ny] <= cost[x][y] + 1) continue;
                    queue.add(new int[]{nx, ny, dist + 1});
                    cost[nx][ny] = cost[x][y] + 1;
                } else {
                    if (cost[nx][ny] <= cost[x][y]) continue;
                    queue.add(new int[]{nx, ny, dist + 1});
                    cost[nx][ny] = cost[x][y];
                }
            }
        }

        System.out.println(-1);
    }
}

/*
*/
