package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n14888_ {

    static int[] numbers;
    static int[] operators = new int[4];

    static int N;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0,  numbers[0]);

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int level, int result) {
        if (level == N - 1) {
            if (max < result) {
                max = result;
            }

            if (min > result) {
                min = result;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] == 0) continue;
            operators[i] -= 1;
            dfs(level + 1, operate(result, numbers[level + 1], i));
            operators[i] += 1;
        }
    }

    static int operate(int num1, int num2, int operator) {
        switch (operator) {
            case 0:
                return num1 + num2;
            case 1:
                return num1 - num2;
            case 2:
                return num1 * num2;
            default:
                return num1 / num2;
        }
    }
}
