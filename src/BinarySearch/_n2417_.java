package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _n2417_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        long start = 0;
        long end = n; // 2^31

        while (start <= end) {
            long mid = (start + end) / 2;
            double v = (double)mid * mid;
            if (v < n) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(start);
    }
}
/*
의사코드
1. n의 제곱근은 반드시 n 보다 작기 때문에 1 ~ n 까지의 범위에 대해 Binary Search 를 수행한다.
2. 다만 n 의 최대값이 2^63 - 1 이기 때문에 초기 mid 값은 약 2^62승이고 이를 제곱하면 long 의 표현 범위를
넘어가게 되므로 계산과정에서 double 형으로의 변경이 필요하다.

시간 복잡도
O(logN)
-> 0 ~ N 까지의 범위에 대해 BinarySearch 를 수행하기 때문에 O(logN)
 */