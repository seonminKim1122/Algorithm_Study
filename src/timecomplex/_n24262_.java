package timecomplex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _n24262_ {

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];

        MenOfPassion(A, n);
        System.out.println(count);
        System.out.println(0);
    }

    public static void MenOfPassion(int[] A, int n) {
        int i = n / 2;
        count++;
    }
}
/*
입력의 크기(n)와 상관 없이 1번 실행되기 때문에
시간복잡도는 O(1) 이다.

수행 횟수 : 1
최고 차수 : 0
 */
