package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n21921_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        int start = 0;
        int end = X - 1;


        int visit = 0;
        for (int i = start; i <= end; i++) {
            visit += numbers[i];
        }

        int maxVisit = visit;
        int cnt = 1;
        while (end < N - 1) {
            visit -= (numbers[start++]);
            visit += numbers[++end];

            if (visit > maxVisit) {
                maxVisit = visit;
                cnt = 1;
            } else if (visit == maxVisit) {
                cnt++;
            }
        }

        if (maxVisit == 0) {
            System.out.println("SAD");
            return;
        }

        System.out.println(maxVisit);
        System.out.println(cnt);
    }
}
/*
의사 코드
1. start = 0, end = X - 1 로 초기화해서 구간합을 구한다.
2. start 와 end 포인터를 이동시키되 구간합 계산을 위해 이전 구간합에서 numbers[start++] 빼고 , numbers[++end] 더하기
3. 구간합을 계산한 경우 이전 최대 구간합과 비교해서 같다면 counting 값을 더해주고
   현재 구간합이 이전 최대 구간합보다 크다면 counting 을 1로 갱신하고 최대 구간합 값을 변경해준다.
4. end 포인터가 배열의 끝에 도달할 때까지 2 ~ 3 과정을 반복한다.
5. 최대 방문자가 0 이면 SAD 를 아니라면 counting 값 출력

시간복잡도
O(N - X)
-> end 포인터가 X - 1 에서 N - 1 까지 이동하기 때문
 */