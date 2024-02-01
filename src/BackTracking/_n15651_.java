package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n15651_ {

    static int N;
    static int M;

    static StringBuilder answer = new StringBuilder();

    static int[] path;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        path = new int[M];
        dfs(0);

        System.out.println(answer);
    }

    static void dfs(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                answer.append(path[i]).append(' ');
            }
            answer.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            path[depth] = i;
            dfs(depth + 1);
            path[depth] = 0;
        }
    }
}
