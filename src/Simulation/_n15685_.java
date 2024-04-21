package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _n15685_ {

    static boolean[][] onDragonCurve = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            DragonCurve dragonCurve = new DragonCurve(x, y);
            dragonCurve.addPoint(d);

            int g = Integer.parseInt(st.nextToken());
            for (int j = 0; j < g; j++) {
                dragonCurve.toNextGeneration();
            }
        }

        int result = 0;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                if (isSquareOnDragonCurve(i, j)) result++;
            }
        }

        System.out.println(result);
    }

    static boolean isSquareOnDragonCurve(int i, int j) {
        if (i + 1 >= 101 || j + 1 >= 101) return false;
        return onDragonCurve[i][j] && onDragonCurve[i + 1][j] && onDragonCurve[i][j + 1] && onDragonCurve[i + 1][j + 1];
    }
    static class DragonCurve {
        List<int[]> points = new ArrayList<>();
        int size = 0;
        int[] lastPoint = new int[2];

        DragonCurve(int x, int y) {
            points.add(new int[]{x, y});
            lastPoint[0] = x;
            lastPoint[1] = y;
            onDragonCurve[x][y] = true;
            size++;
        }

        int calcDirection(int[] from, int[] to) {
            if (to[0] == from[0] + 1) return 0;
            if (to[1] == from[1] - 1) return 1;
            if (to[0] == from[0] - 1) return 2;
            return 3;
        }

        int turnDirection(int d) {
            d--;
            if (d < 0) return 3;
            return d;
        }

        void toNextGeneration() {
            for (int i = size - 1; i > 0; i--) {
                int[] from = points.get(i);
                int[] to = points.get(i - 1);
                int d = turnDirection(calcDirection(from, to));

                addPoint(d);
            }
        }

        void addPoint(int d) {
            int[] nextPoint = new int[2];
            switch (d) {
                case 0:
                    nextPoint[0] = lastPoint[0] + 1;
                    nextPoint[1] = lastPoint[1];
                    break;
                case 1:
                    nextPoint[0] = lastPoint[0];
                    nextPoint[1] = lastPoint[1] - 1;
                    break;
                case 2:
                    nextPoint[0] = lastPoint[0] - 1;
                    nextPoint[1] = lastPoint[1];
                    break;
                default:
                    nextPoint[0] = lastPoint[0];
                    nextPoint[1] = lastPoint[1] + 1;
            }

            points.add(nextPoint);
            lastPoint = nextPoint;
            onDragonCurve[nextPoint[0]][nextPoint[1]] = true;
            size++;
        }
    }
}
/*
[a, b, c, d, e] 가 있을 때

e 를 기준으로 d 까지의 방향 파악, 해당 방향을 시계 방향으로 회전
-> 마지막 좌표 기준 회전된 것을 추가(이제 추가된 게 마지막)

d 를 기준으로 c 까지의 방향 파악, 해당 방향을 시계 방향으로 회전
-> 마지막 좌표 기준 회전된 것을 추가
*/