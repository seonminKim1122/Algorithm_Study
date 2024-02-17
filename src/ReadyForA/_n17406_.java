package ReadyForA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n17406_ {

    static int[][] arr;

    static int N;
    static int M;
    static int K;

    static int[][] spinInfo;
    static int result = Integer.MAX_VALUE;

    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        spinInfo = new int[K][3];
        used = new boolean[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            spinInfo[i][0] = r;
            spinInfo[i][1] = c;
            spinInfo[i][2] = s;
        }

        dfs(0);

        System.out.println(result);
    }

    static void dfs(int level) {
        if (level == K) {
            int minSum = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                int temp = 0;
                for (int j = 0; j < M; j++) {
                    temp += arr[i][j];
                }
                minSum = Math.min(minSum, temp);
            }
            result = Math.min(result, minSum);
            return;
        }

        for (int i = 0; i < K; i++) {
            if (used[i]) continue;

            int r = spinInfo[i][0];
            int c = spinInfo[i][1];
            int s = spinInfo[i][2];
            used[i] = true;
            spin(r, c, s);
            dfs(level + 1);
            reverseSpin(r, c, s);
            used[i] = false;
        }
    }

    static void spin(int r, int c, int s) {

        for (int i = 0; i < s; i++) {
            int startX = r - s + i;
            int startY = c - s + i;
            int endX = r + s - i;
            int endY = c + s - i;

            int value = arr[startX][startY];
            // 왼쪽
            for (int x = startX; x < endX; x++) {
                arr[x][startY] = arr[x + 1][startY];
            }
            // 아래
            for (int y = startY; y < endY; y++) {
                arr[endX][y] = arr[endX][y + 1];
            }
            // 오른쪽
            for (int x = endX; x > startX; x--) {
                arr[x][endY] = arr[x - 1][endY];
            }
            // 위
            for (int y = endY; y > startY; y--) {
                arr[startX][y] = arr[startX][y - 1];
            }

            arr[startX][startY + 1] = value;
        }
    }

    static void reverseSpin(int r, int c, int s) {
        for (int i = 0; i < s; i++) {
            int startX = r - s + i;
            int startY = c - s + i;
            int endX = r + s - i;
            int endY = c + s - i;

            int value = arr[startX][startY];
            // 위
            for (int y = startY; y < endY; y++) {
                arr[startX][y] = arr[startX][y + 1];
            }

            // 오른쪽
            for (int x = startX; x < endX; x++) {
                arr[x][endY] = arr[x + 1][endY];
            }

            // 아래
            for (int y = endY; y > startY; y--) {
                arr[endX][y] = arr[endX][y - 1];
            }

            // 왼쪽
            for (int x = endX; x > startX; x--) {
                arr[x][startY] = arr[x - 1][startY];
            }

            arr[startX + 1][startY] = value;
        }
    }
}


/*
아 사용가능한 회전 연산을 순서 맘대로 해봐야한다!!
 */