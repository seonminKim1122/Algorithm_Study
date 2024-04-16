package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _n16236_ {

    static int N;
    static int[][] space;
    static int sizeOfShark;
    static int needToGrow;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        space = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sizeOfShark = 2;
        needToGrow = 2;
        result = 0;

        while (eat()) {}

        System.out.println(result);
    }

    static boolean eat() {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) ->
                o1[2] - o2[2] != 0 ? o1[2] - o2[2] : (o1[0] - o2[0] != 0 ? o1[0] - o2[0] : o1[1] - o2[1]));
        boolean[][] visited = new boolean[N][N];

        // 현재 상어의 위치 추가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (space[i][j] == 9) {
                    space[i][j] = 0;
                    queue.add(new int[]{i, j, 0});
                    visited[i][j]= true;
                }
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            int fish = space[now[0]][now[1]];
            if (fish != 0 && fish < sizeOfShark) {
                result += now[2];
                needToGrow--;

                if (needToGrow == 0) {
                    sizeOfShark++;
                    needToGrow = sizeOfShark;
                }

                space[now[0]][now[1]] = 9;
                return true;
            }

            for (int k = 0; k < 4; k++) {
                int nx = now[0] + dx[k];
                int ny = now[1] + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (space[nx][ny] > sizeOfShark || visited[nx][ny]) continue;

                queue.add(new int[]{nx, ny, now[2] + 1});
                visited[nx][ny] = true;
            }
        }

        return false;
    }
}
