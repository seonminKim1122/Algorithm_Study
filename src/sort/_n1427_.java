package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.StringBuilder;

public class _n1427_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strNum = br.readLine();

        int[] numbers = new int[strNum.length()];
        for (int i = 0; i < strNum.length(); i++) {
            numbers[i] = strNum.charAt(i) - '0';
        }

        quickSort(numbers, 0, numbers.length - 1);

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            answer.append(numbers[i]);
        }

        System.out.println(answer);
    }

    public static void quickSort(int[] numbers, int left, int right) {
        if (left >= right) return;

        int pivot = numbers[left];
        int low = left + 1;
        int high = right;

        while (low <= high) {
            while (low <= right && numbers[low] >= pivot) {
                low++;
            }

            while (left < high && numbers[high] <= pivot) {
                high--;
            }

            if (low < high) {
                int temp = numbers[low];
                numbers[low] = numbers[high];
                numbers[high] = temp;
            }
        }

        numbers[left] = numbers[high];
        numbers[high] = pivot;

        quickSort(numbers, left, high - 1);
        quickSort(numbers, high + 1, right);
    }
}
/*
N = 1,000,000,000 
-> 총 10자리 숫자

따라서 N^2 방식으로 정렬하더라도 100회의 연산밖에 필요하지 않음
퀵소트로 풀이
 */
