package ReadyForA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_3234 {

    static int N;
    static int[] factorial;
    static int[] weights;
    static int totalWeight;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            weights = new int[N];
            totalWeight = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
                totalWeight += weights[i];
            }

            factorial = new int[N + 1];
            factorial[0] = 1;
            for (int i = 1; i <= N; i++) {
                factorial[i] = factorial[i - 1] * i;
            }

            result = 0;
            boolean[] onScale = new boolean[N];
            solve(0, 0, 0, onScale);

            answer.append('#').append(t).append(' ').append(result).append('\n');
        }

        System.out.println(answer);
    }

    static void solve(int level, int leftWeight, int rightWeight, boolean[] onScale) {
        // 결국 마지막 모든 추를 올렸다면 당연히 왼쪽 무게가 절반 이상일 텐데 따로 걸려줘야 시간초과가 안 난다...?
        if (level == N) {
            result++;
            return;
        }
        // 저울의 왼쪽 무게 합이 전체 절반 이상면 이후 무게추는 아무 곳에나 올려도 된다

        if (2 * leftWeight >= totalWeight) {
            result += (Math.pow(2, N - level) * factorial[N - level]);
            return;
        }

        /*
        추 하나를 저울에 올리기 left or right
         */
        for (int i = 0; i < N; i++) {
            if (onScale[i]) continue;
            onScale[i] = true;
            solve(level + 1, leftWeight + weights[i], rightWeight, onScale);
            if (leftWeight >= rightWeight + weights[i]) {
                solve(level + 1, leftWeight, rightWeight + weights[i], onScale);
            }
            onScale[i] = false;
        }
    }
}
/*
N 개의 무게추를 전부 저울에 올리는 과정에서 오른쪽 저울에 올라간 무게가
왼쪽 저울에 올라간 무게보다 커져서는 안 된다!!

시간 초과. 다른 방법 or 가지치기 더 생각해보기

왼쪽에 올라간 무게가 전체 무게의 절반 이상이라면 나머지는 어디에 올리든 상관이 없다.
-> 즉 더 탐색을 하지 않고 이 때의 경우의 수를 result 에 추가하고 return 해줘도 된다.

총 N 개 중에 X 개를 올린 시점에 왼쪽이 절반 이상이다.
=> N - X 개는 자유롭게 올려도 된다.
(N-X)! * 2^(N-X) 만큼의 경우의 수가 result 에 추가된다!
 */
