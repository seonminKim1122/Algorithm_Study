package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _n14940_ {

    static int[][] map;
    static int n;
    static int m;
    static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        distance = new int[n][m];

        int[] target = new int[2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    target[0] = i;
                    target[1] = j;
                }
            }
        }

        calcDistance(target);

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer.append(distance[i][j]).append(' ');
            }
            answer.append('\n');
        }

        System.out.println(answer);
    }

    static void calcDistance(int[] target) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{target[0], target[1], 0});
        distance[target[0]][target[1]] = n * m;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            int x = now[0];
            int y = now[1];
            int d = now[2];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (distance[nx][ny] != 0 || map[nx][ny] == 0) continue;

                queue.add(new int[]{nx, ny, d + 1});
                distance[nx][ny] = d + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && distance[i][j] == 0) {
                    distance[i][j] = -1;
                }
            }
        }

        distance[target[0]][target[1]] = 0;
    }
}

/*
의사코드
1. 목표지점에서 출발해서 각 지점까지의 거리를 출력한다.(BSF)
2. 더 이상 탐색이 불가능해지면 방문하지 않았는데 원래 갈 수 있던 길(1) 인 곳은 -1로 처리해준다.

시간복잡도
O(n x m)
-> 배열 전체를 탐색하므로 O(n x m) 이다.
 */
