package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n1806_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[] accumulateSum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            accumulateSum[i] = accumulateSum[i - 1] + numbers[i];
        }

        int start = 1;
        int end = 1;
        int minLength = N + 1;
        while (start <= N && end <= N) { // 값 하나가 >= S 를 만족하면 end < start 현상이 발생하는데 이러면 temp 가 0
            int temp = accumulateSum[end] - accumulateSum[start - 1];

            if (temp < S) {
                end++;
            } else {
                minLength = Math.min(minLength, end - start + 1);
                start++;
            }
        }

        System.out.println(minLength == (N + 1) ? 0 : minLength);
    }
}
/*
구간합이 S 이상이 되는 것 중 가장 짧은 구간의 길이를 구하는 프로그램
의사 코드
1. 누적합 배열 생성
2. start 포인터와 end 포인터를 이용해 구간합 계산하기
-> 현재의 구간합이 S 보다 작으면 end 포인터 이동
-> 현재의 구간합이 S 보다 크거나 같으면 start 포인터 이동
    -> start 포인터를 이동하면 구간 길이가 줄어들었으니 이전에 구했던 최소 구간 길이와 비교해서 갱신
3. S <= 인 케이스가 없으면 0 출력


시간복잡도
O(N)
1. 누적합 계산 -> O(N)
2. 투 포인터를 이용한 알고리즘 -> O(N)
 */