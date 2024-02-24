package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class _n1644_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(0);
            return;
        }

        boolean[] isNotPrime = new boolean[N + 1];
        int root = (int)Math.sqrt(N);
        for (int i = 2; i <= root; i++) {
            if (isNotPrime[i]) continue;
            for (int j = i + i; j <= N; j += i) {
                isNotPrime[j] = true;
            }
        }

        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isNotPrime[i]) continue;
            primeNumbers.add(i);
        }

        int start = 0;
        int end = 0;
        int sum = primeNumbers.get(0);
        int size = primeNumbers.size();
        // 연속된 소수의 합 -> 투 포인터 활용

        int result = 0;
        while (end < size) {
            if (sum < N) {
                if (end == size - 1) break;
                sum += primeNumbers.get(++end);
            } else if (sum > N) {
                sum -= primeNumbers.get(start++);
            } else {
                sum -= primeNumbers.get(start++);
                result++;
            }
        }

        System.out.println(result);
    }
}
