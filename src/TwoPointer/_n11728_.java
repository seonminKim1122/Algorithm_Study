package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n11728_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int[] sorted = new int[N + M];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < N && j < M) {
            if (A[i] < B[j]) {
                sorted[k++] = A[i++];
            } else {
                sorted[k++] = B[j++];
            }
        }

        for (int l = i; l < N; l++) {
            sorted[k++] = A[l];
        }

        for (int l = j; l < M; l++) {
            sorted[k++] = B[l];
        }

        StringBuilder answer = new StringBuilder();
        for (int l = 0; l < N + M; l++) {
            answer.append(sorted[l]).append(' ');
        }

        System.out.println(answer);
    }
}
/*
의사 코드
1. 첫 번째 배열과 두 번째 배열에 각각 포인터(i, j) 사용 -> 투 포인터
2. arr1[i] < arr2[j] 라면 arr[1] 을 합친 배열에 넣고 i++
3. 반대라면 arr[2] 를 합친 배열에 넣고 j++
4. 두 포인터 중 하나가 배열의 끝에 도달하면 작업을 중단하고 남은 쪽을 전부 합친 배열에 넣기

시간복잡도 : O(N + M)
결국 N 사이즈 배열의 포인터와 M 사이즈 배열의 포인터가 모두 시작점에서 끝점까지 이동해야하므로
O(N + M)
 */
