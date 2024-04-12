package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _n17135_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int archer1 = 0; archer1 < M - 2; archer1++) {
            for (int archer2 = archer1 + 1; archer2 < M - 1; archer2++) {
                for (int archer3 = archer2 + 1; archer3 < M; archer3++) {
                    int[] archers = {archer1, archer2, archer3};
                    answer = Math.max(answer, solve(grid, archers, D));
                }
            }
        }

        System.out.println(answer);
    }

    static int solve(int[][] arr, int[] archers, int D) {

        int N = arr.length;
        int M = arr[0].length;

        int[][] grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = arr[i][j];
            }
        }

        // 궁수 포지션 : (N, archer1), (N, archer2), (N, archer3)
        int kill = 0;
        while (true) {
            // 궁수들 타겟 찾기
            int[] enemies = new int[3];
            for (int i = 0; i < 3; i++) {
                int archer = archers[i];
                int enemy = findEnemy(grid, archer, D);
                enemies[i] = enemy;
            }

            // 적 사살
            for (int enemy : enemies) {
                if (enemy == -1) continue;

                // 사살!
                int x = enemy / M;
                int y = enemy % M;
                if (grid[x][y] == 1) {
                    grid[x][y] = 0;
                    kill++;
                }
            }
            
            // 남은 적군의 이동
            int leftEnemies = 0;
            for (int col = 0; col < M; col++) {
                for (int row = N - 1; row > 0; row--) {
                    grid[row][col] = grid[row - 1][col];
                    if (grid[row][col] == 1) leftEnemies++;
                }
                grid[0][col] = 0;
            }

            if (leftEnemies == 0) break;
        }

        return kill;
    }

    static int findEnemy(int[][] grid, int archer, int D) {
        int N = grid.length;
        int M = grid[0].length;

        PriorityQueue<int[]> queue = new PriorityQueue<>((p1, p2) -> p1[2] - p2[2] == 0 ? p1[1] - p2[1] : p1[2] - p2[2]); // 왼쪽에 있는 적이 우선 순위
        boolean[][] visit = new boolean[N][M];
        queue.add(new int[]{N - 1, archer, 1});
        visit[N - 1][archer] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int distance = now[2];

            if (grid[x][y] == 1) {
                return x * M + y;
            }

            if (distance == D) continue;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visit[nx][ny]) continue;

                queue.add(new int[]{nx, ny, now[2] + 1});
                visit[nx][ny] = true;
            }
        }

        return -1;
    }
}