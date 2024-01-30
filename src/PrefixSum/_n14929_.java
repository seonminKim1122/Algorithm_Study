package PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n14929_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[] S = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            S[i] = S[i - 1] + numbers[i];
        }

        long result = 0;
        for (int i = 1; i < n; i++) {
            result += (numbers[i] * (long)(S[n] - S[i]));
        }

        System.out.println(result);
    }
}
/*
문제에서 주어진 식을 전개해보면
x1*x2 + x1*x3 + ... + x1*xn
+ x2*x3 + x2*x4 + ... + x2*xn
...
+ xn-1*xn
이다.

의사 코드
1. 누적합 배열을 만든다.
2. x1*(S[n]-S[1]) + x2*(S[n]-S[2]) ... + xn-1*(S[n]-S[n-1]) 을 통해 정답을 구한다.

시간복잡도
- O(2N - 1) -> O(N)
- 누적합 배열 만드는 데 O(N)
- 누적합 배열을 이용해 정답 구하는 데 O(N-1)
 */