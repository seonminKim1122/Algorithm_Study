package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _n4179_ {

    static char[][] miro;
    static int R;
    static int C;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        miro = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                miro[i][j] = line.charAt(j);
            }
        }

        // # : 벽, . : 지나갈 수 있는 공간, J : 지훈이 초기 위치, F : 불
        int result = solve();
        System.out.println(result == -1 ? "IMPOSSIBLE" : result);
    }

    static int solve() {
        Queue<int[]> jihoonQueue = new LinkedList<>();
        Queue<int[]> fireQueue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (miro[i][j] == 'J') {
                    jihoonQueue.add(new int[]{i, j, 0});
                    miro[i][j] = '.';
                    visited[i][j] = true;
                } else if (miro[i][j] == 'F') {
                    fireQueue.add(new int[]{i, j});
                }
            }
        }

        boolean[] burned = new boolean[R + C + 1];
        while (!jihoonQueue.isEmpty()) {
            int[] temp = jihoonQueue.poll();
            
            int x = temp[0];
            int y = temp[1];
            int time = temp[2];

            if (!burned[time + 1]) {
                // 불태우기
                burn(fireQueue);
                burned[time + 1] = true;
            }

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                    return time + 1;
                }

                if (miro[nx][ny] == '#' || miro[nx][ny] == 'F' || visited[nx][ny]) continue;
                jihoonQueue.add(new int[]{nx, ny, time + 1});
                visited[nx][ny] = true;
            }
        }

        return -1;
    }

    static void burn(Queue<int[]> fireQueue) {
        int len = fireQueue.size();

        for (int i = 0; i < len; i++) {
            int[] fireTemp = fireQueue.poll();

            int fireX = fireTemp[0];
            int fireY = fireTemp[1];

            for (int k = 0; k < 4; k++) {
                int nx = fireX + dx[k];
                int ny = fireY + dy[k];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (miro[nx][ny] == '#' || miro[nx][ny] == 'F') continue;

                miro[nx][ny] = 'F';
                fireQueue.add(new int[]{nx, ny});
            }
        }
    }
}
