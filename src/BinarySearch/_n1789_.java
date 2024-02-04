package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _n1789_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(br.readLine());

        int start = 1;
        int end = 92683;

        while (start <= end) {
            int mid = (start + end) / 2;
            long v = (long)mid * (mid + 1) / 2;

            if (v <= S) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(end);
    }
}
/*
의사 코드
1. 1 ~ N 까지의 합 N(N+1)/2 를 계산한다.
2. 이 값이 S 이하이면 start 를 키우고, S 초과이면 end 를 줄이는 방식으로 최적해를 찾는다.
3. 서로 다른 N 개의 합이 S 가 되게 하는 가장 큰 N 을 찾는데 1 ~ N 까지의 합을 이용하는 이유는
이 값이 서로 다른 N 개가 가지는 최소값이기 때문이다. -> 값을 하나 추가하면 S 를 넘어가는 지점까지
왔다면 값을 하나 교체함으로써 S 를 만들 수 있다.

시간복잡도
-> O(log92683)
-> 1 ~ 92683까지의 합이 S의 최대값 보다 살짝 커서 이렇게 범위를 설정했다.
-> 만약 S 를 end 포인트로 설정하면 시간복잡도는 O(logS)다
 */