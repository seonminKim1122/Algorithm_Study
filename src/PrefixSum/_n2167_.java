package PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n2167_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] S = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                S[i][j] = S[i - 1][j] + S[i][j - 1] - S[i - 1][j - 1] + arr[i - 1][j - 1];
            }
        }

        int K = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < K; i++) {
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
1. 입력된 배열을 통해 누적합 배열을 만든다 S[i][j] -> (1, 1) ~ (i, j) 까지의 직사각형 합
2. S[i][j] = S[i - 1][j] + S[i][j - 1] - S[i - 1][j - 1] + numbers[i - 1][j - 1]
3. 구간합을 구할 prefixSum 메서드를 만든다.
    -> (x1, y1, x2, y2, S) 를 입력으로 받아 (x1, y1) ~ (x2, y2) 의 구간합 반환

시간복잡도
-> O(N * M + K)
-> 누적합 배열을 만드는데에 N * M 연산이 필요하고 실제 구간합을 구하는 방식은 O(1) 이 소요된다.
-> 구간합을 구할 부분의 개수가 K 이므로 전체 코드의 시간복잡도는 O(N * M + K) 이다
 */