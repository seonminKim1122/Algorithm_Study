package NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _n14565_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        long A = Long.parseLong(st.nextToken());

        StringBuilder answer = new StringBuilder();
        answer.append(modularAdditionReverse(A, N)).append(' ');



        long result = modularMultipleReverse(A, N, 1, 0, 0, 1);
        if (result == Long.MIN_VALUE) {
            answer.append(-1);
        } else if (result <= 0) {
            result += N;
            answer.append(result);
        } else {
            answer.append(result);
        }

        System.out.println(answer);
    }


    static long modularAdditionReverse(long A, long N) {
        return N - A;
    }

    static long modularMultipleReverse(long r0, long r1, long s0, long s1, long t0, long t1) {
        if (r1 == 0) {
            if (r0 == 1) return s0;
            else return Long.MIN_VALUE;
        }

        long q = r0 / r1;
        long r = r0 % r1;
        long s = s0 - q * s1;
        long t = t0 - q * t1;

        return modularMultipleReverse(r1, r, s1, s, t1, t);
    }
}
/*
의사코드
1. 덧셈 역원 구하기
-> (a + b) mod n = 0 이라는 것은 a % n + b % n = n 이라는 의미이다
-> a 가 주어졌을 때 n - a % n 을 구하면 이것이 덧셈 역원이 된다.

2. 곱셈 역원 구하기
-> ac + ny = 1 을 만족하는 c 를 찾으면 이것이 모듈러 곱셈 역원
이는 확장된 유클리드 호제법을 이용해 구할 수 있다.
-> 만약 구해진 해가 음수라면 해당 수에 N 을 더해서 양의 해를 구할 수 있다.

N 의 범위가 1조까지 허용되는데 확장된 유클리드 호제법은 대략 logN 의 시간복잡도를 가지므로
1초 안에 통과가 가능할 것으로 예상된다.
 */