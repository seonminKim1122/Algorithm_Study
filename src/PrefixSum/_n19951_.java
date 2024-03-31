package PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n19951_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 연병장의 크기
        int M = Integer.parseInt(st.nextToken()); // 조교의 수

        int[] H = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        int[] orders = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            orders[a] += k;
            if (b + 1 <= N) orders[b + 1] -= k;
        }

        int[] totalOrder = new int[N + 1];
        totalOrder[1] = orders[1];
        for (int i = 2; i <= N; i++) {
            totalOrder[i] = totalOrder[i - 1] + orders[i];
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            answer.append(H[i] + totalOrder[i]).append(' ');
        }

        System.out.println(answer);
    }
}
/*
a ~ b 까지는 -3 하세요니까 b + 1 에 3을 넣어서 여기부터는 -3의 영향력이 없게 해라

0 0 0 0 0 0 0 0 0 0
-3 0 0 0 0 3 0 0 0 0

-3 0 0 0 0 8 0 0 0 0
-3 2 0 0 0 8 0 -2 0 0

-3 -1 -1 -1 -1  7  7  5  5  5
 1  2  3  4  5 -1 -2 -3 -4 -5

-2 1 2 3 4 6 5 2 1 0

dp[a] += K
dp[b] -= K

a ~ b 까지 k 를 더하는 연산을 M 번하고
최종 결과 배열을 출력...
*/