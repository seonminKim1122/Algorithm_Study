package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _n16928_ {

    static int[] ladders = new int[101];
    static int[] snakes = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 사다리 정보
        Arrays.fill(ladders, -1);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            ladders[x] = y;
        }

        // 뱀 정보
        Arrays.fill(snakes, -1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            snakes[u] = v;
        }

        System.out.println(solve());
    }

    static int solve() {
        boolean[] board = new boolean[101];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});
        board[1] = true;

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();

            int now = temp[0];
            int turn = temp[1];

            if (now == 100) return turn;

            for (int i = 1; i <= 6; i++) {
                int next = now + i;
                if (next > 100) continue;
                if (ladders[next] != -1) next = ladders[next];
                if (snakes[next] != -1) next = snakes[next];
                if (board[next]) continue; // 간적 있는 곳이면 pass

                queue.add(new int[]{next, turn + 1});
                board[next] = true;
            }
        }

        return -1;
    }
}