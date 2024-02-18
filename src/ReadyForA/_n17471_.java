package ReadyForA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n17471_ {


    static int N;
    static int[][] graph;

    static boolean[] selected;
    static int[] population;
    static int totalPopulation = 0;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // 인구 정보 입력
        population = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            totalPopulation += population[i];
        }

        // 연결 정보 입력
        graph = new int[N][N];
        for (int from = 0; from < N; from++) {
            st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            for (int j = 0; j < M; j++) {
                int to = Integer.parseInt(st.nextToken()) - 1;
                graph[from][to] = 1;
            }
        }

        // solve
        selected = new boolean[N];
        solve(0, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
    
    static void solve(int level, int start) { // 1 ~ N / 2 개 구역 뽑기(조합)
        if (level > (N / 2)) return;

        if (level >= 1 && check(level)) { // level 명 뽑힌 상태 선거구 조건 성립 시
            // 두 그룹 인원 차 계산
            int temp = 0;
            for (int i = 0; i < N; i++) {
                if (selected[i]) temp += population[i];
            }
            int diff = Math.abs(totalPopulation - 2 * temp);
            if (min > diff) {
                min = diff;
            }
        }

        for (int i = start; i < N; i++) {
            selected[i] = true;
            solve(level + 1, i + 1);
            selected[i] = false;
        }
    }
    
    static boolean check(int cnt) { // 선거구 성립 여부 확인 메서드
        int[] district1 = new int[cnt];
        int[] district2 = new int[N - cnt];

        int idx1 = 0;
        int idx2 = 0;
        for (int i = 0; i < N; i++) {
            if (selected[i]) {
                district1[idx1++] = i;
            } else {
                district2[idx2++] = i;
            }
        }
        /*
        각 선거구(district) 에 대해 성립 여부 확인 - how?
        1. district1 차례때는 selected[i] == false 인 곳은 못 가게 dfs 수행
        2. 최종 결과에서 selected[i] == true 인 곳을 모두 visit 했는지 확인
        
        3. district2 차례때는 selected[i] == true 인 곳은 못 가게 dfs 수행
        4. 최종 결과에서 selected[i] == false 인 곳을 모두 visit 했는지 확인
        */
        boolean[] visit = new boolean[N];
        // district1 탐색
        district1DFS(district1[0], visit);
        for (int i = 0; i < N; i++) {
            if (selected[i]) {
                if (visit[i]) {
                    visit[i] = false;
                } else {
                    return false;
                }
            }
        }
        
        // district2 탐색
        district2DFS(district2[0], visit);
        for (int i = 0; i < N; i++) {
            if (!selected[i]) {
                if (!visit[i]) return false;
            }
        }

        return true;
    }

    static void district1DFS(int now, boolean[] visit) {
        visit[now] = true;

        for (int next = 0; next < N; next++) {
            if (visit[next] || !selected[next]) continue;
            if (graph[now][next] == 0) continue;

            district1DFS(next, visit);
        }
    }

    static void district2DFS(int now, boolean[] visit) {
        visit[now] = true;

        for (int next = 0; next < N; next++) {
            if (visit[next] || selected[next]) continue;
            if (graph[now][next] == 0) continue;

            district2DFS(next, visit);
        }
    }
}
/*
1. 1 ~ N - 1개 구역 뽑기!( 1개 뽑기나 N - 1개 뽑기나 동일하므로 N / 2 개 뽑기까지만 가도 된다)
2. 뽑은 구역을 하나의 선거구로 묶고 성립 가능한지 체크
-> 뽑은 애들끼리 연결됐는지(1번째 뽑은 애를 기준으로 그래프 탐색 수행, 이 때 다른 그룹이면 못 지나간다!)
-> 안 뽑힌 애들끼리 연결됐는지(안 뽑힌 1번째를 기준으로 그래프 탐색 수행, 이 때 다른 그룹이면 못 지나간다!)

3. 성립 가능하다면 두 선거구의 인구 차를 계산
4. 인구 차 min 값 갱신

------------------------------------------
 */
