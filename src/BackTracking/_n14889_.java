package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _n14889_ {

    static boolean[] isStartTeam;
    static int N;
    static int[][] graph;

    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isStartTeam = new boolean[N];
        dfs(0, 0);

        System.out.println(result);
    }

    static void dfs(int level, int start) {
        /*
        점수 차이 계산 파트
         */
        if (level == N / 2) {
            result = Math.min(result, calcDiff());
            return;
        }
        
        /*
        팀 선정 파트
         */
        for (int i = start; i < N; i++) {
            isStartTeam[i] = true;
            dfs(level + 1, i + 1);
            isStartTeam[i] = false;
        }
    }

    static int calcDiff() {
        int startTeamPower = 0;
        int linkTeamPower = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isStartTeam[i] && isStartTeam[j]) {
                    startTeamPower += (graph[i][j] + graph[j][i]);
                } else if (!isStartTeam[i] && !isStartTeam[j]) {
                    linkTeamPower += (graph[i][j] + graph[j][i]);
                }
            }
        }

        return Math.abs(startTeamPower - linkTeamPower);
    }
}
