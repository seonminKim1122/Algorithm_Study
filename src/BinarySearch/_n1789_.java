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
1 ~ N 까지의 합 N(N+1)/2 가 S 이하이면서 가장 S 랑 가까운 N 이 정답지이다.
어차피 부족한 부분은 다른 숫자를 교체하면 되니까!
 */