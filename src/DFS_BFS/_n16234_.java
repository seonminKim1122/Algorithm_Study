package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _n16234_ {

    static int populationSum;
    static int cnt;
    static int[][] populations;
    static int N;
    static int L;
    static int R;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<int[]> union;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        populations = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                populations[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            boolean[][] isUnion = new boolean[N][N];
            int movedCnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!isUnion[i][j]) {
                        union = new ArrayList<>();
                        populationSum = 0;
                        cnt = 0;

                        dfs(i, j, isUnion);
                        movedCnt += move();
                    }
                }
            }
            
            // 인구 이동
            if (movedCnt == 0) break;
            day++;
        }

        System.out.println(day);
    }

    static void dfs(int i, int j, boolean[][] isUnion) {
        isUnion[i][j] = true;
        cnt++;
        populationSum += populations[i][j];
        union.add(new int[]{i, j});

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (isUnion[nx][ny]) continue;
            
            int diff = Math.abs(populations[i][j] - populations[nx][ny]);
            if (diff < L || diff > R) continue;

            dfs(nx, ny, isUnion);
        }
    }

    static int move() {
        int value = populationSum / cnt;

        int result = 0;
        for (int i = 0; i < union.size(); i++) {
            int[] now = union.get(i);
            if (populations[now[0]][now[1]] != value) {
                populations[now[0]][now[1]] = value;
                result = 1;
            }
        }

        return result;
    }
}
/*
의사코드
1. 아직 연합에 속하지 않은 경우 DFS 수행해서 연합을 만든다.
-> 연합을 만들 때 방문한 나라의 인구 누적합을 구하고, 칸 누적합도 구한다.
2. 연합끼리 인구 이동을 시도한다.
-> 구해놓은 누적합/누적칸 을 활용
3. 인구 이동이 발생했다면 인구 이동이 발생했다고 카운팅 해주고 넘어간다.
4. 발생하지 않았다면 0을 리턴한다.
위 과정을 총 인구 이동 발생횟수가 0일 때까지 반복

시간복잡도
O(N^2)
*/