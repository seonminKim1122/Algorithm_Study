package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n20057_ {

    static int[][] sandX = {
            {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0}, // 좌
            {0, 1, 0, -1, 2, 1, 0, -1, 0, 1}, // 하
            {2, 1, 1, 1, 0, -1, -1, -1, -2, 0}, // 우
            {0, -1, 0, 1, -2, -1, 0, 1, 0, -1}, // 상
    };

    static int[][] sandY = {
            {0, -1, 0, 1, -2, -1, 0, 1, 0, -1}, // 좌
            {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0}, // 하
            {0, 1, 0, -1, 2, 1, 0, -1, 0, 1}, // 우
            {2, 1, 1, 1, 0, -1, -1, -1, -2, 0}, // 상
    };

    static double[] ratios = {
            0.02, 0.1, 0.07, 0.01, 0.05, 0.1, 0.07, 0.01, 0.02
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = N / 2;
        int y = N / 2;

        int length = 1;
        int direction = 0;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        int answer = 0;
        loop: while (!(x == 0 && y == 0)) {
            // Let's go
            for (int l = 0; l < length; l++) {
                if (x == 0 && y == 0) break loop;
                x = x + dx[direction];
                y = y + dy[direction];
                answer += blow(x, y, map, direction);
            }
            direction = (direction + 1) % 4;

            for (int l = 0; l < length; l++) {
                if (x == 0 && y == 0) break loop;
                x = x + dx[direction];
                y = y + dy[direction];
                answer += blow(x, y, map, direction);
            }
            direction = (direction + 1) % 4;
            length++;
        }

        System.out.println(answer);
    }

    static int blow(int x, int y, int[][] map, int direction) {
        int origin = map[x][y];

        int N = map.length;
        int result = 0;
        for (int k = 0; k <= 9; k++) {
            int nx = x + sandX[direction][k];
            int ny = y + sandY[direction][k];

            int sand;
            if (k == 9) {
                sand = map[x][y];
            } else {
                sand = (int)(origin * ratios[k]);
            }

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                result += sand;
            } else {
                map[nx][ny] += sand;
            }

            map[x][y] -= sand;
        }
        map[x][y] = 0;

        return result;
    }
}