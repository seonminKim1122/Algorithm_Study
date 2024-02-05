package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _n16918_ {

    static int[][] map;
    static int R;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken()); // N 초 후 격자판 상태

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                if (line.charAt(j) == '.') {
                    map[i][j] = -1; // 음수면 폭탄이 없는 것
                } else {
                    map[i][j] = 2; // 어차피 1초 아무것도 하지 않을 것이므로 3이 아니라 2로 할당
                }
            }
        }

        // 처음 1초는 아무것도 하지 않는다
        N -= 1;

        while (N > 0) {
            // 다음 1초 폭탄 설치
            tiktok();
            plantBomb();
            N--;

            if (N == 0) break;
            // 다음 1초 폭발
            tiktok();
            explode();
            N--;
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] < 0) answer.append('.');
                else answer.append('O');
            }
            answer.append('\n');
        }

        System.out.println(answer);
    }

    static void plantBomb() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] < 0) map[i][j] = 3;
            }
        }
    }

    static void tiktok() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] -= 1;
            }
        }
    }

    static void explode() {
        Queue<int[]> bombs = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0) bombs.add(new int[]{i, j});
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!bombs.isEmpty()) {
            int[] bomb = bombs.poll();

            for (int k = 0; k < 4; k++) {
                int nx = bomb[0] + dx[k];
                int ny = bomb[1] + dy[k];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                map[nx][ny] = -1;
            }
            map[bomb[0]][bomb[1]] = -1;
        }
    }
}
/*
의사코드
1. 폭탄이 있는 곳은 폭탄이 터지기 까지의 시간을 나타내고, 폭탄이 없는 곳은 -1로 표현
2. 폭탄을 설치할 차례라면 -1인 곳에 폭탄 설치(3)
3. 시간이 흐르면 폭탄이 터지기 까지의 시간들을 모두 1씩 감소
4. 값이 0인 곳은 현재 터져야 하는 폭탄들이므로 Queue 에 저장한 후 하나씩 뽑으면서 인접한 4칸 및 본인까지 터뜨림 (-1)

시간복잡도
: O(N^2)
 */