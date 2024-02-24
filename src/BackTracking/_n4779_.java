package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _n4779_ {

    static char[] cantoa;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;

        StringBuilder answer = new StringBuilder();
        while ((input = br.readLine()) != null) {
            int N = Integer.parseInt(input);

            int len = (int)Math.pow(3, N);
            cantoa = new char[len];
            Arrays.fill(cantoa, '-');

            solve(0, len);

            for (int i = 0; i < len; i++) {
                answer.append(cantoa[i]);
            }
            answer.append('\n');
        }

        System.out.println(answer);
    }

    static void solve(int start, int end) {
        if (end - start == 1) return;

        int term = (end - start) / 3;
        solve(start, start + term);
        for (int i = start + term; i < start + 2 * term; i++) {
            cantoa[i] = ' ';
        }
        solve(start + 2 * term, end);
    }
}
