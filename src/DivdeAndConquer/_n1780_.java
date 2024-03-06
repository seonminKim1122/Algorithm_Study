package DivdeAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n1780_ {

    static int[] result = {0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, N - 1, N - 1, paper);
        for (int answer : result) {
            System.out.println(answer);
        }
    }

    static void solve(int x1, int y1, int x2, int y2, int[][] paper) {
        if (isSame(x1, y1, x2, y2, paper)) {
            int idx = paper[x1][y1] + 1;
            result[idx]++;
            return;
        }

        // 종이를 같은 크기 9개로 자르기
        int xDiff = (x2 - x1 + 1) / 3;
        int yDiff = (y2 - y1 + 1) / 3;

        solve(x1, y1, x1 + xDiff - 1, y1 + yDiff - 1, paper);
        solve(x1, y1 + yDiff, x1 + xDiff - 1, y1 + 2 * yDiff - 1, paper);
        solve(x1, y1 + 2 * yDiff, x1 + xDiff - 1, y2, paper);

        solve(x1 + xDiff, y1, x1 + 2 * xDiff - 1, y1 + yDiff - 1, paper);
        solve(x1 + xDiff, y1 + yDiff, x1 + 2 * xDiff - 1, y1 + 2 * yDiff - 1, paper);
        solve(x1 + xDiff, y1 + 2 * yDiff, x1 + 2 * xDiff - 1, y2, paper);

        solve(x1 + 2 * xDiff, y1, x2, y1 + yDiff - 1, paper);
        solve(x1 + 2 * xDiff, y1 + yDiff, x2, y1 + 2 * yDiff - 1, paper);
        solve(x1 + 2 * xDiff, y1 + 2 * yDiff, x2, y2, paper);

    }

    static boolean isSame(int x1, int y1, int x2, int y2, int[][] paper) {
        int value = paper[x1][y1];
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (paper[i][j] != value) return false;
            }
        }

        return true;
    }
}