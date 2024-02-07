package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _n7576_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] tomatoes = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tomatoes[i][j] = Integer.parseInt(st.nextToken());
                if (tomatoes[i][j] == 1) {
                    queue.add(new int[]{i, j, 0});
                }
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int result = 0;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = now[0] + dx[k];
                int ny = now[1] + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (tomatoes[nx][ny] != 0) continue;

                tomatoes[nx][ny] = 1;
                queue.add(new int[]{nx, ny, now[2] + 1});
            }

            if (queue.isEmpty()) {
                result = now[2];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomatoes[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(result);
    }
}
/*
의사코드
1. 토마토 저장 정보를 받아 초기에 익은 토마토들을 큐에 넣는다.
2. BFS 를 수행하는데 {토마토 X, 토마토 Y, 며칠 차에 익었는지} 를 큐에 넣고 다음 토마토가 익으면 {토마토 NX, 토마토 NY, 이전 + 1}
3. 토마토가 들어있지 않은 칸(-1) 은 지나간다
4. 더 이상 탐색할 수 없을 때가 되면 남은 배열에 익지 않은 토마토(0) 이 존재하는지 확인한다.
5. 익지 않은 토마토가 남아있다면 -1 을 그렇지 않다면 다 익는데 소요된 일수를 출력한다.
 */