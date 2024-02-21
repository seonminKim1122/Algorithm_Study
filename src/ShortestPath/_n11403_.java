package ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class _n11403_ {

    static int N;
    static int[][] graph;
    static int[][] dist;
    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> arr = new ArrayList<>();

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dist = new int[N][N];
        floydWarshall();

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dist[i][j] == INF) {
                    answer.append(0).append(' ');
                } else {
                    answer.append(1).append(' ');
                }
            }
            answer.append('\n');
        }

        System.out.println(answer);
    }

    static void floydWarshall() {
        // dist 배열 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 1) dist[i][j] = 1;
            }
        }

        // floyd-warshall
        for (int via = 0; via < N; via++) {
            for (int from = 0; from < N; from++) {
                for (int to = 0; to < N; to++) {
                    if (dist[from][to] > dist[from][via] + dist[via][to]) {
                        dist[from][to] = dist[from][via] + dist[via][to];
                    }
                }
            }
        }
    }
}

/*
의사코드
1. 모든 정점에 대해 양수인 경로가 있는지 확인하기 위해서는 '플로이드-워셜' 알고리즘을 사용한다.
2. 플로이드 워셜 알고리즘을 통해 모든 정점 간의 최단 경로를 구하고 이 값이 양수인 곳은 1, 아닌 곳은 0 으로 출력한다.
3. 단, 갈 수 없는 경우(INF) 가 존재할 수 있으므로 INF 인 경우에도 0이 될 수 있게 한다.

시간복잡도
O(N^3)
-> 플로이드-워셜 알고리즘은 N개의 노드에 대해서 각각을 중간 노드로 하여 움직일 수 있는 케이스를 모두 탐색하므로
N^3 이 시간복잡도를 갖는다.
 */
