package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _n2751_ {

    static int[] sortedNumbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        sortedNumbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(numbers, 0, N - 1);
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            answer.append(numbers[i]).append("\n");
        }

        System.out.println(answer);
    }

    public static void mergeSort(int[] numbers, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(numbers, left, mid);
        mergeSort(numbers, mid + 1, right);
        merge(numbers, left, mid, right);
    }

    public static void merge(int[] numbers, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (numbers[i] < numbers[j]) {
                sortedNumbers[k++] = numbers[i++];
            } else {
                sortedNumbers[k++] = numbers[j++];
            }
        }

        for (int l = i; l <= mid; l++) {
            sortedNumbers[k++] = numbers[l];
        }

        for (int l = j; l <= right; l++) {
            sortedNumbers[k++] = numbers[l];
        }

        for (int l = left; l <= right; l++) {
            numbers[l] = sortedNumbers[l];
        }
    }
}
/*
퀵 소트 알고리즘
- 최악의 경우 O(N^2)
- 이상적이면 O(NlogN)

Big-O 표기법은 최악의 상황을 가정하므로 현재 코드의 시간복잡도 = O(N^2)
-> 예제는 N 이 최대 1,000,000 이므로 2초의 제한 시간 내에 통과 못 할 수 있음
-> 실제로 시간초과 발생

듀얼 피봇 퀵 소트 -> Arrays.sort()
-> 최악의 경우 O(N^2)
-> 이상적인 경우 pivot 이 2개이므로 O(Nlog3N) 까지 가능

안정적으로 O(NlogN) 의 시간복잡도를 갖는 Merge Sort 를 통해 이를 구현하자
-> mergeSort 는 크게 분할 과정 -> 병합 과정으로 나뉘고 실제 정렬은 병합 단계에서 일어난다
-> 분할은 index 를 통해 현재 분할된 배열의 시작과 끝을 나타내는 방식 및 재귀를 통해 구현이 가능하고
-> 병합은 mergeSort 가 정렬하는 과정을 그대록 구현하면 된다.
-> 다만 이 때 정렬하면서 바로 원본에 덮어쓰면 문제가 생기므로 정렬된 값을 담을 임시배열이 필요하다.

실제 결과
Arrays.sort -> 1368ms
mergeSort -> 928ms
 */