package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _n3273_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        int num1Idx = 0;
        int num2Idx = n - 1;

        int result = 0;
        Arrays.sort(numbers);
        while (num1Idx < num2Idx) {
            int temp = numbers[num1Idx] + numbers[num2Idx];
            if (temp == x) {
                result++;
                num1Idx++;
                num2Idx--;
            } else if (temp < x) {
                num1Idx++;
            } else {
                num2Idx--;
            }
        }

        System.out.println(result);
    }
}
