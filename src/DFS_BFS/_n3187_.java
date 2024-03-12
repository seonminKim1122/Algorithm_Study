package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _n3187_ {

    static int numOfSheep = 0;
    static int numOfWolf = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] village = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                village[i][j] = line.charAt(j);
                if (village[i][j] == 'v') {
                    numOfWolf++;
                } else if (village[i][j] == 'k') {
                    numOfSheep++;
                }
            }
        }

        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (village[i][j] != '#' && !visited[i][j]) {
                    // 작업!
                    solve(i, j, village, visited);
                }
            }
        }

        System.out.println(numOfSheep + " " + numOfWolf);
    }

    static void solve(int i, int j, char[][] village, boolean[][] visited) {
        int R = village.length;
        int C = village[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;

        int sheep = 0;
        int wolf = 0;
        if (village[i][j] == 'v') {
            wolf++;
        } else if (village[i][j] == 'k') {
            sheep++;
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = now[0] + dx[k];
                int ny = now[1] + dy[k];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (visited[nx][ny] || village[nx][ny] == '#') continue;

                if (village[nx][ny] == 'v') {
                    wolf++;
                } else if (village[nx][ny] == 'k') {
                    sheep++;
                }

                queue.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }

        if (sheep > wolf) {
            numOfWolf -= wolf;
        } else {
            numOfSheep -= sheep;
        }
    }
}
/*
울타리가 아닌 곳 만나면 탐색
-> 울타리 내부에 양의 숫자, 늑대의 숫자 카운팅
-> 양 > 늑대 : 늑대가 잡아먹힘 (늑대 총 숫자에서 빼주기)
-> 양 <= 늑대 : 양이 전부 잡아먹힘 (양 총 숫자에서 빼주기)


 */