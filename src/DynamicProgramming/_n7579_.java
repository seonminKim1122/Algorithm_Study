package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n7579_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 앱 갯수
        int M = Integer.parseInt(st.nextToken()); // 필요한 메모리

        int[] apps = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            apps[i] = Integer.parseInt(st.nextToken());
        }
        int[] costs = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        int sumOfCost = 0;
        for (int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            sumOfCost += costs[i];
        }

        int[][] dp = new int[N + 1][sumOfCost + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < costs[i]; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            for (int j = costs[i]; j <= sumOfCost; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - costs[i]] + apps[i]);
            }
        }

        int result = 0;
        for (int cost = 0; cost <= sumOfCost; cost++) {
            if (dp[N][cost] >= M) {
                result = cost;
                break;
            }
        }

        System.out.println(result);
    }
}
/*
의사코드
1. dp[i][j] 를 i번째 앱까지 고려했을 때 j원으로 얻을 수 있는 최대 메모리
0 <= < cost[i] : dp[i][j] = dp[i - 1][j]
cost[i] <= : dp[i][j] = Math.max(dp[i - 1][j - cost[i]] + app[i],  dp[i - 1][j])

2. 0 <= cost <= sumOfCost 중 dp[i][cost] >= M 을 만족하는 최소 cost 찾으면 정답

시간복잡도
O(N * sumOfCost) -> 최대 100 x 10000
 */