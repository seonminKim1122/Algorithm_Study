package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n2512_ {


    static int[] budgets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        budgets = new int[N];

        for (int i = 0; i < N; i++) {
            budgets[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        int start = 1;
        int end = getMax(budgets);

        while (start <= end) {
            int mid = (start + end) / 2;

            int temp = calc(mid);

            if (temp <= M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(end);
    }

    static int calc(int upperLimit) {
        int result = 0;

        for (int i = 0; i < budgets.length; i++) {
            result += Math.min(upperLimit, budgets[i]);
        }

        return result;
    }

    static int getMax(int[] budgets) {
        int result = budgets[0];
        for (int i = 1; i < budgets.length; i++) {
            result = Math.max(result, budgets[i]);
        }

        return result;
    }
}
/*
의사 코드
1. 정수 상한선(X)을 설정한다.
2. 이 때 배정된 예산의 총액을 계산한 후 총 예산(M) 보다 작으면 상한선을 키우고
3. 총예산(M)보다 크다면 상한선을 낮춘다.
4. 이러한 BinarySearch 방식으로 총예산 보다 크지 않으면서 최대한 가까운 배정 예산을 만드는 상한선을 찾는다.
5. 단, 정수 상한선은 예산 중 가장 큰 값보다 클 때는 아무런 의미가 없으므로 end 값은 예산 중 max 값으로 설정한다.

시간복잡도
-> O(N * logK)
-> N : 입력값의 개수
-> K : 입력값 중 Max 값
-> Binary Search 의 범위가 1 ~ K 이므로 O(logK) 의 시간이 소요되는덷
-> 매 탐색때마다 배정 예산의 합을 계산해야 해서 O(N) 의 시간이 소요된다.

N 은 최대 10,000 K는 최대 100,000 이므로 1초는 거뜬
 */