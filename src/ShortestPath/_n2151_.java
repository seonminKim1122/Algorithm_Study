package ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _n2151_ {

    public static void main(String[] args) throws IOException {
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        
        // 로직
        System.out.println(solve(N, map));
    }

    static int solve(int N, char[][] map) {
        List<int[]> doors = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '#') {
                    doors.add(new int[]{i, j});
                }
            }
        }

        int[] start = doors.get(0);
        int[][][] mirrors = new int[4][N][N];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(mirrors[i][j], N * N + 1);
            }
            mirrors[i][start[0]][start[1]] = 0;
        }
        // direction(0 : 상, 1 : 하, 2 : 좌, 3 : 우)
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[3] - o2[3]);
        for (int k = 0; k < 4; k++) {
            int nx = start[0] + dx[k];
            int ny = start[1] + dy[k];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (map[nx][ny] == '*') continue;

            pq.add(new int[]{nx, ny, k, 0});
        }

        int[] end = doors.get(1);
        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            int x = now[0];
            int y = now[1];
            int direction = now[2];
            int mirror = now[3];

            if (x == end[0] && y == end[1]) {
                return mirror;
            }
            // 내 현재 위치에 거울이 있으면 방향 전환 추가
            if (map[x][y] == '!') {
                if (direction <= 1) {
                    for (int k = 2; k < 4; k++) {
                        if (mirrors[k][x][y] <= mirror + 1) continue;
                        pq.add(new int[]{x, y, k, mirror + 1});
                        mirrors[k][x][y] = mirror + 1;
                    }
                } else {
                    for (int k = 0; k < 2; k++) {
                        if (mirrors[k][x][y] <= mirror + 1) continue;
                        pq.add(new int[]{x, y, k, mirror + 1});
                        mirrors[k][x][y] = mirror + 1;
                    }
                }
            }

            // 지금 빛의 방향대로 움직이기
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (map[nx][ny] == '*') continue;
            if (mirrors[direction][nx][ny] <= mirror) continue;
            pq.add(new int[]{nx, ny, direction, mirror});
            mirrors[direction][nx][ny] = mirror;
        }

        return -1;
    }
}
/*
다익스트라 (거울 갯수가 비용 )
문 하나에서 시작
상, 하, 좌, 우 방향으로 빛 출발
거울을 설치할 수 있는 위치에 만났을 때 방향을 꺾는 경우 => 거울 갯수 + 1
그냥 원래 방향으로 가는 경우 => 거울 갯수 유지

반대편 문에 도착했을 때 거울 갯수를 출력하자!
 */