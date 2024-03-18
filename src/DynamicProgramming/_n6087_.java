package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _n6087_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        char[][] map = new char[H][W];
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'C') {
                    points.add(new int[]{i, j});
                }
            }
        }

        System.out.println(solve(map, points));
    }

    static int solve(char[][] map, List<int[]> points) {
        int H = map.length;
        int W = map[0].length;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        /*
        direction
        0 : 상
        1 : 하
        2 : 좌
        3 : 우
         */
        // 초기화 작업
        int[] start = points.get(0);
        int[] end = points.get(1);

        int[][][] costs = new int[4][H][W];
        for (int d = 0; d < 4; d++) {
            for (int i = 0; i < H; i++) {
                Arrays.fill(costs[d][i], H * W + 1);
            }
            costs[d][start[0]][start[1]] = 0;
        }


        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int k = 0; k < 4; k++) {
            int nx = start[0] + dx[k];
            int ny = start[1] + dy[k];

            if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
            if (map[nx][ny] == '*') continue;

            pq.add(new int[]{0, nx, ny, k});
            costs[k][nx][ny] = 0;
        }

        // 다익스트라
        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            int mirrors = now[0];
            int x = now[1];
            int y = now[2];
            int direction = now[3];

            if (x == end[0] && y == end[1]) {
                return mirrors;
            }

            for (int k = 0; k < 4; k++) {
                if (direction + k == 1 || direction + k == 5) continue;

                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if (map[nx][ny] == '*') continue;

                int nextCost = mirrors;
                if (k != direction) {
                    nextCost++;
                }

                if (costs[k][nx][ny] <= nextCost) continue;
                costs[k][nx][ny] = nextCost;
                pq.add(new int[]{nextCost, nx, ny, k});
            }
        }
        
        return -1;
    }
}

/*
같은 위치, 이전과 동일한 거울 갯수를 사용한 경우도 레이저 방향에 따라 다를 수 있으므로
PQ 에 == 인 케이스는 넣어줘야 한다

특정 좌표에 같은 비용, 같은 방향으로 방문한 중복 케이스에 대한 처리를 해줘야 한다...
 */