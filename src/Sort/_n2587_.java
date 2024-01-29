package Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _n2587_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] numbers = new int[5];
        for (int i = 0; i < 5; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int[] sortedNumbers = insertionSort(numbers);

        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += sortedNumbers[i];
        }

        StringBuilder answer = new StringBuilder();
        answer.append(sum / 5).append("\n");
        answer.append(sortedNumbers[5 / 2]);

        System.out.println(answer);
        System.out.println(Arrays.toString(sortedNumbers));
    }

    public static int[] insertionSort(int[] numbers) {
        int N = numbers.length;

        for (int i = 1; i < N; i++) {
            int num = numbers[i];
            int j = i - 1;
            while (j >= 0) {
                if (numbers[j] > num) {
                    numbers[j + 1] = numbers[j];
                } else {
                    break;
                }
                j--;
            }
            numbers[j + 1] = num;
        }

        return numbers;
    }
}
/*
삽입 정렬 -> O(N^2)
 */
