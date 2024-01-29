package PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n14929_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[] S = new int[n + 1]; // S[n] -> x1 + x2 + ... + xn
        for (int i = 1; i <= n; i++) {
            S[i] = S[i - 1] + numbers[i - 1];
        }

        long result = 0;
        for (int i = 1; i <= n - 1; i++) {
            result += (S[i] * numbers[i]);
        }

        System.out.println(result);
    }
}
/*
xn(x1 + ... + xn-1)
+ xn-1(x1 + ... + xn-2)
...
+ x2x1

을 구하는 문제이다.

의사코드
1. 누적합 배열 S 를 만든다
2. S[n-1] * X[n] + ... + S[1] * X[2] 를 구하면 된다.

시간복잡도
-> O(N)
-> 1차원 배열이므로 누적합 배열을 만들 때 O(N) 이 소요되고
-> 문제에서 요구한 것도 누적합 배열을 이용하며 O(N) 으로 구할 수 있다.
 */