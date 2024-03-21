package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n2468_ {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[][] heightInfo = new int[N][N];

        int end = 1;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                heightInfo[i][j] = Integer.parseInt(st.nextToken());
                if (end < heightInfo[i][j]) end = heightInfo[i][j];
            }
        }

        int result = 1;
        for (int rain = 1; rain <= end; rain++) {
            // rain 이하인 곳은 못 가는 지역으로 취급해서 dfs 몇 번 돌아가는지 수행
            int cnt = 0;
            boolean[][] visit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (heightInfo[i][j] <= rain || visit[i][j]) continue;
                    
                    // dfs 수행
                    dfs(visit, heightInfo, i, j, rain);
                    cnt++;
                }
            }
            result = Math.max(result, cnt);
        }

        System.out.println(result);
    }

    static void dfs(boolean[][] visit, int[][] heightInfo, int i, int j, int rain) {
        visit[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (visit[nx][ny] || heightInfo[nx][ny] <= rain) continue;

            dfs(visit, heightInfo, nx, ny, rain);
        }
    }
}
