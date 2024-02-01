package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n15650_ {

    static int N;
    static int M;

    static int[] path;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        path = new int[M];

        permutation(0, 1);

        System.out.println(answer);
    }

    static void permutation(int depth, int start) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                answer.append(path[i]).append(' ');
            }
            answer.append('\n');
            return;
        }

        for (int i = start; i <= N; i++) {
            path[depth] = i;
            permutation(depth + 1, i + 1);
            path[depth] = 0;
        }
    }
}

/*
의사코드
1. 중복되는 수열을 출력하면 안되므로 1번 노드부터 탐색을 시작했다면 2번을 첫 노드로 할 때는 1번을 탐색하지 않도록 한다.

시간복잡도
-> O(N!)
 */
