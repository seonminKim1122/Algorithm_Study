package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class _n2750_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int[] sortedNumbers = selectionSort(numbers);
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            answer.append(sortedNumbers[i]).append("\n");
        }

        System.out.println(answer);
    }

    public static int[] selectionSort(int[] numbers) {
        int N = numbers.length;
        for (int i = 0; i < N - 1; i++) {
            int min = numbers[i];
            for (int j = i + 1; j < N; j++) {
                if (numbers[j] < min) {
                    min = numbers[j];
                    numbers[j] = numbers[i];
                    numbers[i] = min;
                }
            }
        }

        return numbers;
    }
}
/*
선택정렬 사용한 방식은 O(N^2)
 */
