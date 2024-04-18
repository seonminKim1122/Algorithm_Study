package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _n16985_ {

    static int[][][] path = new int[5][5][5];
    static boolean[] used = new boolean[5];
    static int[][][] maze;
    static int result = 5 * 5 * 5 + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        maze = new int[5][5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    maze[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        solve(0);

        System.out.println(result == 5 * 5 * 5 + 1 ? -1 : result);
    }
    
    static void solve(int level) { // 판을 임의의 순서(순열)로 배치하는 메서드
        if (result == 12) return; // 12가 5 x 5 x 5 3차원 미로에서 나올 수 있는 최소값임
        
        if (level == 5) {
            // BFS() 수행
            int temp = BFS(path);
            result = Math.min(result, temp);
            return;
        }

        for (int i = 0; i < 5; i++) { // 현재 높이에 쌓일 판 선택
            if (used[i]) continue;
            used[i] = true;
            for (int turn = 0; turn < 4; turn++) { // 선택한 판을 회전
                path[level] = turnRight(maze[i], turn);
                solve(level + 1);
            }
            used[i] = false;
        }
    }

    static int BFS(int[][][] cube) {
        if (cube[0][0][0] == 0) return 5 * 5 * 5 + 1;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[5][5][5];
        queue.add(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;

        int[] dz = {0, 0, 0, 0, 1, -1};
        int[] dx = {-1, 1, 0, 0, 0, 0};
        int[] dy = {0, 0, -1, 1, 0, 0};

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            int z = now[0];
            int x = now[1];
            int y = now[2];

            if (z == 4 && x == 4 && y == 4) {
                return now[3];
            }

            for (int k = 0; k < 6; k++) {
                int nz = z + dz[k];
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nz < 0 || nx < 0 || ny < 0 || nz >= 5 || nx >= 5 || ny >= 5) continue;
                if (cube[nz][nx][ny] == 0 || visited[nz][nx][ny]) continue;

                queue.add(new int[]{nz, nx, ny, now[3] + 1});
                visited[nz][nx][ny] = true;
            }
        }

        return 5 * 5 * 5 + 1;
    }

    static int[][] turnRight(int[][] board, int turn) { // 판을 회전시키는 메서드
        int[][] newBoard = new int[5][5];

        if (turn == 0) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newBoard[i][j] = board[i][j];
                }
            }
        } else if (turn == 1) {
            for (int col = 0; col < 5; col++) {
                for (int row = 4; row >= 0; row--) {
                    newBoard[col][4 - row] = board[row][col];
                }
            }
        } else if (turn == 2) {
            for (int row = 4; row >= 0; row--) {
                for (int col = 4; col >= 0; col--) {
                    newBoard[4 - row][4 - col] = board[row][col];
                }
            }
        } else {
            for (int col = 4; col >= 0; col--) {
                for (int row = 0; row < 5; row++) {
                    newBoard[4 - col][row] = board[row][col];
                }
            }
        }

        return newBoard;
    }
}