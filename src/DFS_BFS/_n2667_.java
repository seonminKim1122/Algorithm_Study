package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _n2667_ {
    
    static int[][] graph;
    static int house = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }

        int cnt = 0;
        List<Integer> houses = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 1) {
                    house = 0;
                    cnt++;
                    dfs(i, j);
                    houses.add(house);
                }
            }
        }

        Collections.sort(houses);

        StringBuilder answer = new StringBuilder();
        answer.append(cnt).append('\n');
        for (int i = 0; i < houses.size(); i++) {
            answer.append(houses.get(i)).append('\n');
        }

        System.out.println(answer);
    }

    static void dfs(int i, int j) {
        graph[i][j] = 0;
        house++;

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (graph[nx][ny] == 0) continue;

            dfs(nx, ny);
        }
    }
}
/*
의사코드
1. 집이 있는 곳에서 dfs() 수행해서 같은 단지의 집을 모두 탐색
2. 단지 내 집 탐색 때마다 카운팅 하여 단지 내 집의 수 파악
3. 전체 배열에 대해 해당 과정을 반복하고 dfs() 가 몇 번 수행됐는지를 통해 단지 수 파악

시간복잡도
O(N^2)
*/