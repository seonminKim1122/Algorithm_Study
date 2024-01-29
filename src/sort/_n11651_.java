package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n11651_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] numbers = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            numbers[i][0] = Integer.parseInt(st.nextToken());
            numbers[i][1] = Integer.parseInt(st.nextToken());
        }

        mergeSort(numbers, 0, N - 1);

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            answer.append(numbers[i][0]).append(" ").append(numbers[i][1]).append("\n");
        }

        System.out.println(answer);
    }

    public static void mergeSort(int[][] numbers, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(numbers, left, mid);
        mergeSort(numbers, mid + 1, right);
        merge(numbers, left, mid, right);
    }

    public static void merge(int[][] numbers, int left, int mid, int right) {
        int[][] temp = new int[right - left + 1][2];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (isLarger(numbers[i], numbers[j])) {
                temp[k++] = numbers[j++];
            } else {
                temp[k++] = numbers[i++];
            }
        }

        for (int l = i; l <= mid; l++) {
            temp[k++] = numbers[l];
        }

        for (int l = j; l <= right; l++) {
            temp[k++] = numbers[l];
        }

        for (int l = left; l <= right; l++) {
            numbers[l] = temp[l - left];
        }
    }

    public static boolean isLarger(int[] A, int[] B) {
        int xa = A[0];
        int ya = A[1];
        int xb = B[0];
        int yb = B[1];

        if (ya > yb) {
            return true;
        } else if (ya == yb) {
            if (xa >= xb) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
/*
점의 개수가 최대 100,000 개이기 때문에 N^2 으로 연산하면
10,000,000,000 이므로 1초안에 통과 불가능
따라서 NlogN 의 시간복잡도를 갖는 mergeSort 사용
다만 좌표간 대소비교는 기본 연산자로 할 수 없으므로 따로 메서드로 만들어서 사용!

시간복잡도 : O(NlogN)
 */