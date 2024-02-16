package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n17404_ {

    static int INF = 1001 * 1001;
    static int result = INF;
    static int[][] dp;
    static int N;
    static int[][] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        costs = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = INF;
        // 1번이 Red 일때
        dp = new int[N][N];
        dp[0][1] = dp[0][2] = INF;
        dp[0][0] = costs[0][0];

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0], INF);
            dp[i][1] = Math.min(Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1], INF);
            dp[i][2] = Math.min(Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2], INF);
        }
        result = Math.min(result, Math.min(dp[N-1][1], dp[N-1][2]));
        // 1번이 Green 일때
        dp = new int[N][N];
        dp[0][0] = dp[0][2] = INF;
        dp[0][1] = costs[0][1];

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0], INF);
            dp[i][1] = Math.min(Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1], INF);
            dp[i][2] = Math.min(Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2], INF);
        }
        result = Math.min(result, Math.min(dp[N-1][0], dp[N-1][2]));

        // 1번이 Blue 일때
        dp = new int[N][N];
        dp[0][0] = dp[0][1] = INF;
        dp[0][2] = costs[0][2];

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0], INF);
            dp[i][1] = Math.min(Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1], INF);
            dp[i][2] = Math.min(Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2], INF);
        }
        result = Math.min(result, Math.min(dp[N-1][0], dp[N-1][1]));

        System.out.println(result);
    }
}
