package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _n14916_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int fiveCoins = n / 5;
        n %= 5;

        int twoCoins = n / 2;
        n %= 2;

        if (n == 0) {
            System.out.println(fiveCoins + twoCoins);
        } else {
            fiveCoins -= 1;
            n += 5;
            twoCoins += n / 2;
            System.out.println(fiveCoins < 0 ? -1 : fiveCoins + twoCoins);
        }
    }
}

/*
의사코드
1. 5원짜리로 최대로 거슬러 준 후 나머지를 2원짜리로 나눠준다.
2. 나머지가 1 일 때 5원짜리 1개 차감 하고 6원만큼을 2원짜리로 추가로 거슬러줌

시간복잡도
O(1)
-> 입력값의 크기 n 에 상관없이 연산 횟수 동일
*/
