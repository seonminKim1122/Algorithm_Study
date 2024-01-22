package timecomplex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _n24263_ {

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n + 1];
        MenOfPassion(A, n);

        System.out.println(count);
        System.out.println(1);
    }

    public static int MenOfPassion(int[] A, int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += A[i]; // 코드1
            count++;
        }
        return sum;
    }
}
/*
다항식으로 나타내면 N 이므로 최고 차항은 1
 */