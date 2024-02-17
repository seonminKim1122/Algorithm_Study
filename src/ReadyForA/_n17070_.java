package ReadyForA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n17070_ {
    
    static int[][] graph;
    static int N;
    static int cnt = 0;
    
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

        dfs(0, 1, 0);

        System.out.println(cnt);
    }
    
    static void dfs(int x, int y, int state) {
        if (x == N - 1 && y == N - 1) {
            cnt += 1;
            return;
        }

        // 대각선 움직임
        if (canMove(x, y, 2)) {
            dfs(x + 1, y + 1, 2);
        }
        // 오른쪽 움직임
        if (state != 1 && canMove(x, y, 0)) {
            dfs(x, y + 1, 0);
        }
        // 아래 움직임
        if (state != 0 && canMove(x, y, 1)) {
            dfs(x + 1, y, 1);
        }
    }

    static boolean canMove(int i, int j, int state) {
        if (state == 0) { // 가로
            if (j + 1 >= N) return false;
            if (graph[i][j + 1] == 1) return false;
            return true;
        } else if (state == 1) { // 세로
            if (i + 1 >= N) return false;
            if (graph[i + 1][j] == 1) return false;
            return true;
        } else { // 대각선
            if (i + 1 >= N || j + 1 >= N) return false;
            if (graph[i + 1][j] == 1 || graph[i][j + 1] == 1 || graph[i + 1][j + 1] == 1) return false;
            return true;
        }
    }
}
/*
파이프 초기 위치
(0, 0) ~ (0, 1)
파이프의 오른쪽 끝을 (N - 1, N - 1) 로 옮기는 문제
파이프의 오른쪽 끝이 같은 좌표를 가리키더라도 어떤 방법으로 왔는 가에 따라 다른 방법

파이프가 누워있으면 오른쪽과 대각선 움직임이 가능
파이프가 세워져 있으면 아래와 대각선 움직임이 가능
파이프가 대각선 이면 오른쪽, 아래, 대각선 움직임이 가능
*/