package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _n17836_ {

    static int[][] map;

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solve(T);
        System.out.println(result == -1 ? "Fail" : result);
    }

    static int solve(int T) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0, 0});
        map[0][0] = 1;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            int x = now[0];
            int y = now[1];
            int time = now[2];
            int hasGram = now[3];

            if (time > T) continue;
            if (x == N - 1 && y == M - 1) return time;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (hasGram == 0) { // 그람이 없을 때
                    if (map[nx][ny] == 1 || map[nx][ny] == 3) continue;
                    if (map[nx][ny] == 2) { // 그람을 얻은 경우
                        queue.add(new int[]{nx, ny, time + 1, 1});
                        map[nx][ny] = 3;
                    } else {
                        queue.add(new int[]{nx, ny, time + 1, 0});
                        map[nx][ny] = 1;
                    }
                } else { // 그람이 있을 때
                    if (map[nx][ny] == 3) continue;
                    queue.add(new int[]{nx, ny, time + 1, 1});
                    map[nx][ny] = 3;
                }

            }
        }

        return -1;
    }
}
/*
의사코드
1. BFS 를 통해 용사를 이동시킨다. ({x, y, time, hasGram})
-> 인접한 4칸을 이동할 수 있고 벽으로 막힌 칸은 이동할 수 없다.
-> 최단 시간으로 공주에게 도달해야 하므로 한 번 갔던 칸은 다시 가지 않는다.
-> 단 동일한 칸이라도 그람을 가지고 방문했는가, 아니냐에 따라 추후 이동 경로가 달라질 수 있다.
-> 따라서 일반적으로 방문한 곳은 1로 처리해서 그람 보유 시 지나갈 수 있게 하고 그람을 보유한 채로 방문한 곳은 3
으로 처리해서 절대 다시 가지 못하도록 한다
-> T 시간이 초과될 때까지 공주를 만나지 못한다면 Fail 을 출력한다

시간복잡도
O(N * M) -> 100 x 100 이므로 1초 이내 통과 가능
 */