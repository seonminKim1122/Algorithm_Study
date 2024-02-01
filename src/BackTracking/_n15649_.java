package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n15649_ {

    static int[] path;
    static boolean[] via;
    static StringBuilder answer = new StringBuilder();

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        path = new int[M];
        via = new boolean[N + 1];

        backTracking(0);

        System.out.println(answer);
    }

    static void backTracking(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                answer.append(path[i]).append(' ');
            }
            answer.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (via[i]) continue;
            via[i] = true;
            path[depth] = i;
            backTracking(depth + 1);
            via[i] = false;
            path[depth] = 0;
        }
    }
}
/*
의사코드
1. 백트래킹 방식을 이용해 탐색한 depth == M 이 되는 경우 더 탐색을 하지 않고 현재까지 지나온 노드를 저장한 후
2. 다시 이전 노드로 돌아가는 방식을 통해 문제에서 요구하는 값을 구한다.

시간복잡도
-> O(N!/(N-M)!)
 */
