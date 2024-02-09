package NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n9613_ {

    static int[] numbers;
    static long sumOfGCD;
    static int n;
    static int[] path = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            numbers = new int[n];
            for (int j = 0; j < n; j++) {
                numbers[j] = Integer.parseInt(st.nextToken());
            }
            sumOfGCD = 0;
            combination(0, 0);

            answer.append(sumOfGCD).append('\n');
        }

        System.out.println(answer);
    }

    static void combination(int depth, int start) {
        if (depth == 2) {
            int A = Math.max(path[0], path[1]);
            int B = Math.min(path[0], path[1]);
            sumOfGCD += GCD(A, B);
            return;
        }

        for (int i = start; i < n; i++) {
            path[depth] = numbers[i];
            combination(depth + 1, i + 1);
            path[depth] = 0;
        }
    }

    static int GCD(int A, int B) {
        if (A % B == 0) return B;
        return GCD(B, A % B);
    }
}
/*
의사코드
1. 주어진 N개의 숫자에서 2개를 뽑는다(조합)
2. 뽑은 2개의 숫자의 GCD 를 구한다
-> 유클리드 호제법을 이용
3. 구한 GCD 를 누적해서 모든 쌍의 GCD 합을 출력한다.

시간복잡도
-> O(2^N * logMAX(a, b))
모든 조합을 구하는 것은 N 개의 숫자에 대해 해당 숫자를 뽑는 경우, 뽑지 않는 경우 2가지 선택을
하는 것이므로 전부 탐색하면 2^N 이 소요된다.
구해진 하나의 경우의 수에 대해 유클리드 호제법을 계산하는데 유클리드 호제법은
시간복잡도가 logA 이다.(A > B)

100C2 -> 4950
1000000
495 * 1,000,000 -> 49억5천만 이라서 int 범위를 넘어선다.
*/