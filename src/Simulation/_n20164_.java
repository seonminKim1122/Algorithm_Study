package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _n20164_ {

    static int min;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N = br.readLine();
        min = Integer.MAX_VALUE;
        solve(N, 0);
        System.out.println(min + " " + max);
    }

    static void solve(String N, int odds) {
        odds += numOfOdds(N);
        int len = N.length();
        if (len == 1) {
            min = Math.min(min, odds);
            max = Math.max(max, odds);
            return;
        }

        if (len == 2) {
            // 각 자리 합치기
            solve(String.valueOf((N.charAt(0) - '0') + (N.charAt(1) - '0')), odds);
        } else {
            // 2개의 포인터 써서 구간 나누기
            for (int p1 = 1; p1 <= len - 2; p1++) {
                for (int p2 = p1+1; p2 <= len - 1; p2++) {
                    // 0 ~ p1 전까지, p1 ~ p2 전까지, p2 에서 끝까지
                    int temp = Integer.parseInt(N.substring(0, p1)) +
                               Integer.parseInt(N.substring(p1, p2)) +
                               Integer.parseInt(N.substring(p2, len));
                    solve(String.valueOf(temp), odds);
                }
            }
        }
    }

    static int numOfOdds(String N) {
        int length = N.length();
        int result = 0;
        for (int i = 0; i < length; i++) {
            int remain = (N.charAt(i) - '0') % 2;
            if (remain != 0) {
                result++;
            }
        }
        return result;
    }
}
