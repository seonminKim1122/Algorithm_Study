package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _n2751v2_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        int r = 1;
        for (int i = 0; i < N; i++) {
            String number = br.readLine();
            numbers[i] = Integer.parseInt(number);
            if (number.length() > r) {
                r = number.length();
            }
        }

        int[] sorted = radixSort(r, numbers);
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            answer.append(sorted[i]).append("\n");
        }

        System.out.println(answer);
    }

    public static int[] radixSort(int r, int[] numbers) {
        List<Queue<Integer>> plusQueues = new ArrayList<>();
        List<Queue<Integer>> minusQueues = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            plusQueues.add(new LinkedList<>());
        }

        for (int i = 0; i < 10; i++) {
            minusQueues.add(new LinkedList<>());
        }

        int div = 1;
        for (int i = 0; i < r; i++) {
            div *= 10;
            for (int j = 0; j < numbers.length; j++) {
                int num = numbers[j];
                int digit = (num % div - (num % (div / 10))) / (div / 10);
                if (digit >= 0) plusQueues.get(digit).add(num); // 음수값에서 문제 발생
                if (digit < 0) minusQueues.get(10 + digit).add(num);
            }

            int idx = 0;
            for (int j = 1; j < 10; j++) {
                Queue<Integer> minusQueue = minusQueues.get(j);
                while (!minusQueue.isEmpty()) {
                    int num = minusQueue.poll();
                    numbers[idx++] = num;
                }
            }

            for (int j = 0; j < 10; j++) {
                Queue<Integer> plusQueue = plusQueues.get(j);
                while (!plusQueue.isEmpty()) {
                    int num = plusQueue.poll();
                    numbers[idx++] = num;
                }
            }
        }

        return numbers;
    }
}
