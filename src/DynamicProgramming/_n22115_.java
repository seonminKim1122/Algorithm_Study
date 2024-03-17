package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _n22115_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); // K
        int INF = N + 1;

        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], INF);
        }

        int[] C = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            C[i] = Integer.parseInt(st.nextToken());
            if (K >= C[i]) {
                dp[i][C[i]] = 1;
            }
        }

        if (K == 0) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (K >= C[i]) {
                for (int j = 1; j < C[i]; j++) {
                    dp[i][j] = dp[i - 1][j];
                }

                for (int j = C[i] + 1; j <= K; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - C[i]] + 1);
                }
            } else {
                for (int j = 1; j <= K; j++) {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N][K] >= INF ? -1 : dp[N][K]);
    }
}