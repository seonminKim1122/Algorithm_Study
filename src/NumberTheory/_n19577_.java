package NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _n19577_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int end = (int)Math.sqrt(n);
        for (int i = 1; i <= end; i++) {
            if (n % i != 0) continue;
            if (i * phi(i) == n) {
                System.out.println(i);
                return;
            } else if (phi(n / i) * (n / i) == n) {
                System.out.println(n / i);
                return;
            }
        }

        System.out.println(-1);
    }

    static int phi(int n) {
        int result = n;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i != 0) continue;
            result -= (result / i);
            while (n % i == 0) {
                n /= i;
            }
        }

        if (n > 1) {
            result -= (result / n);
        }

        return result;
    }
}
/*
의사코드
1. 오일러피 함수를 구현한다
2. 1 ~ n 까지 탐색하며 i * phi[i] == n 인 케이스를 찾아 return
-> 시간초과 발생
-> 1 ~ sqrt(n) 까지 탐색해서 i * phi(i) == n 또는 (n / i) * phi(n / i) == n 인 케이스 찾아 return
3. 없으면 -1 출력

시간복잡도
-> 오일러피 함수 : O(sqrt(n))
    -> while 문이 logN 만큼의 시간복잡도를 갖지만 대신 그만큼 바깥 for 문이 줄어드니 sqrt(n)
-> 전체 코드 : O(n)
 */