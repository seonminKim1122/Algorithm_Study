package ReadyForA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n17281_ {

    static int[] path = new int[9];
    static boolean[] used = new boolean[9];

    static int N;
    static int[][] info;

    static int result = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        info = new int[N][9];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // dfs
        path[3] = 0;
        used[0] = true;
        dfs(0);
        System.out.println(result);
    }

    static void dfs(int level) {
        if (level == 9) {
            // 게임해서 점수 계산
            int temp = playGame();
            result = Math.max(result, temp);
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (used[i]) continue;
            path[level] = i;
            used[i] = true;
            if (level == 2) {
                dfs(level + 2);
            } else {
                dfs(level + 1);
            }
            path[level] = 0;
            used[i] = false;
        }
    }

    static int playGame() {
        int now = 0;
        int result = 0;
        
        for (int i = 0; i < N; i++) { // 총 N 이닝 진행
            int[] nowInfo = info[i]; // i번째 이닝 정보

            int[] base = {-1, -1, -1, -1}; // 0 : 베이스, 1 : 1루, 2 : 2루, 3:3루
            int out = 0;
            while (out < 3) {
                int player = path[now];
                now = (now + 1) % 9;

                int hit = nowInfo[player];
                switch (hit) {
                    case 0:
                        // 아웃
                        out++;
                        break;
                    case 1:
                        base[0] = player;
                        // 점수 계산
                        if (base[3] != -1) {
                            result++;
                        }
                        // 진루
                        for (int j = 3; j > 0; j--) {
                            base[j] = base[j - 1];
                        }
                        base[0] = -1;
                        break;
                    case 2:
                        base[0] = player;
                        // 점수 계산
                        for (int j = 3; j >= 2; j--) {
                            if (base[j] != -1) {
                                result++;
                            }
                        }
                        // 진루
                        for (int j = 3; j > 1; j--) {
                            base[j] = base[j - 2];
                        }
                        base[1] = -1;
                        base[0] = -1;
                        break;
                    case 3:
                        // 점수 계산
                        base[0] = player;
                        for (int j = 3; j >= 1; j--) {
                            if (base[j] != -1) {
                                result++;
                            }
                        }
                        // 진루
                        base[3] = base[0];
                        base[2] = -1;
                        base[1] = -1;
                        base[0] = -1;
                        break;
                    default:
                        // 홈런
                        base[0] = player;
                        for (int j = 3; j >= 0; j--) {
                            if (base[j] != -1) {
                                result++;
                                base[j] = -1;
                            }
                        }
                }
            }
        }

        return result;
    }
}
/*
각 이닝 별 각 선수의 결과가 미리 주어진다
결과 -> 안타, 2루타, 3루타, 홈런, 아웃

선수 순서를 잘 정해서 가장 많은 득점을 하는 타순을 찾고 그 때의 점수를 구해보는 문제!
 */