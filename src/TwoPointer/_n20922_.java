package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n20922_ {

    static int[] DAT = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        DAT[numbers[0]]++;
        int LS = 1;
        while (end < N - 1) {
            if (DAT[numbers[end + 1]] < K) {
                DAT[numbers[++end]]++;
                LS = Math.max(LS, end - start + 1);
            } else {
                DAT[numbers[start++]]--;
            }
        }

        System.out.println(LS);
    }
}
/*
의사 코드
1. DAT 을 만든다.(최대 idx : 100,000)
2. start 포인터와 end 포인터의 위치를 0, 0 으로 초기화 하고 DAT[numbers[0]]++ 해준다.
3. 구하고자 하는 바가 최장 연속 부분 수열이므로 end 포인터를 이동시킨다
-> end 포인터를 이동시키면 최장 연속 부분 수열의 길이가 증가하므로 이를 갱신한다!!
-> DAT[numbers[end+1]] < K 이면 가능!
-> DAT[numbers[++end]]++;
4. end 포인터를 늘릴 수 없다면 start 포인터를 이동시킨다.
-> DAT[numbers[start++]]--;

시간복잡도
-> O(N)
-> end 포인터가 배열의 끝에 도달하면 끝난다!
 */