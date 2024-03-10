package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _n20208_ {

    static int homeX = -1;
    static int homeY = -1;
    static int result = 0;

    static List<int[]> milks = new ArrayList<>();
    static boolean[] drank;
    static int H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 데이터 입력 받기
        int N = Integer.parseInt(st.nextToken()); // 민초마을 크기
        int M = Integer.parseInt(st.nextToken()); // 진우 초기체력
        H = Integer.parseInt(st.nextToken()); // 민초우유 마실 때마다 증가하는 체력
        
        int[][] mincho = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                mincho[i][j] = Integer.parseInt(st.nextToken());
                if (mincho[i][j] == 1) {
                    homeX = i;
                    homeY = j;
                } else if (mincho[i][j] == 2) {
                    milks.add(new int[]{i, j});
                }
            }
        }
        
        // solve
        drank = new boolean[milks.size()];
        solve(homeX, homeY, M, 0);

        System.out.println(result);
    }

    static void solve(int x, int y, int stamina, int drinks) {

        // 민초 우유를 1개라도 마시고 집으로 돌아왔다면 result 갱신
        if (drinks > 0 && x == homeX && y == homeY) {
            result = Math.max(result, drinks);
            return;
        }
        
        // 민초 더 먹으러 가기
        for (int i = 0; i < milks.size(); i++) {
            if (drank[i]) continue;
            int nx = milks.get(i)[0];
            int ny = milks.get(i)[1];

            int nextStamina = stamina - (Math.abs(x - nx) + Math.abs(y - ny));
            if (nextStamina < 0) continue;

            drank[i] = true;
            solve(nx, ny, nextStamina + H, drinks + 1);
            drank[i] = false;
        }

        // 집으로 돌아오기
        int nextStamina = stamina - (Math.abs(x - homeX) + Math.abs(y - homeY));
        if (nextStamina < 0 || drinks <= 0) return;

        solve(homeX, homeY, nextStamina, drinks);
    }

}
