package PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n11660_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] S = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                S[i][j] = S[i - 1][j] + S[i][j - 1] - S[i - 1][j - 1] + arr[i - 1][j - 1];
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            answer.append(getPrefixSum(x1, y1, x2, y2, S)).append('\n');
        }

        System.out.println(answer);
    }

    static int getPrefixSum(int x1, int y1, int x2, int y2, int[][] S) {
        return S[x2][y2] - S[x1 - 1][y2] - S[x2][y1 - 1] + S[x1 - 1][y1 - 1];
    }
}
/*
의사 코드
1. N * M 배열을 입력 받는다.
2. 누적합 배열 S 를 만든다.
3. 누적합 배열을 활용해 구간합을 구한다.

시간복잡도
-> O(N * N + M)
-> 누적합 배열 만드는 데에 O(N * N)
-> M 개의 구간합 구하는 데에 O(M)
 */
