package DivdeAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _n1992_ {

    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(solve(0, 0, N - 1, N - 1));
    }

    static String solve(int x1, int y1, int x2, int y2) {
        // (x, x) ~ (y, y) 까지 같은 숫자인지 체크!
        int temp = 0;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                temp += arr[i][j];
            }
        }

        if (temp == 0) return "0";
        if (temp == (x2 - x1 + 1) * (y2 - y1 + 1)) return "1";

        int midX = x1 + (x2 - x1) / 2;
        int midY = y1 + (y2 - y1) / 2;

        // 왼쪽 위
        String result1 = solve(x1, y1, midX, midY);
        // 오른쪽 위
        String result2 = solve(x1, midY + 1, midX, y2);
        // 왼쪽 아래
        String result3 = solve(midX + 1, y1, x2, midY);
        // 오른쪽 아래
        String result4 = solve(midX + 1, midY + 1, x2, y2);
        return "("+result1+result2+result3+result4+")";
    }
}
/*
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 1 1 1 1
0 0 0 0 1 1 1 1
0 0 0 1 1 1 1 1
0 0 1 1 1 1 1 1
0 0 1 1 1 1 1 1
0 0 1 1 1 1 1 1
 */