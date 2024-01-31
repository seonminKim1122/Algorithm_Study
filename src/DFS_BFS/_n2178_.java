package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _n2178_ {

    static int[][] graph;

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                graph[i][j] = input.charAt(j) - '0';
            }
        }

        System.out.println(bfs(new Node(0, 0, 1)));
    }

    static int bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        visited[node.x][node.y] = true;
        queue.add(node);

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = now.x + dx[k];
                int ny = now.y + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny] || graph[nx][ny] == 0) continue;

                if (nx == N - 1 && ny == M - 1) {
                    return now.path + 1;
                }

                queue.add(new Node(nx, ny, now.path + 1));
                visited[nx][ny] = true;
            }
        }

        return -1;
    }

    static class Node {
        int x;
        int y;
        int path;
        Node (int i, int j, int path) {
            this.x = i;
            this.y = j;
            this.path = path;
        }
    }
}
/*
의사코드
1. (0, 0) 에서 BFS 시작
2. 특정 위치에 도착했을 때 지나온 칸의 수 = path 에 저장
3. 위치가 (N - 1, M - 1) 에 도달하면 지금까지 지나온 칸의 수 + 1 리턴

시간복잡도
-> BFS 탐색을 결국 이동 가능한 경로를 다 탐색하되 너비를 우선으로 하는 방식일 뿐이므로
-> O(N * M)
 */