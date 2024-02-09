package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _n13549_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(BFS(N, K));
    }

    static int BFS(int N, int K) {
        Queue<Integer> queue = new LinkedList<>();
        int[] visit = new int[100001];
        Arrays.fill(visit, 100001);
        queue.add(N);
        visit[N] = 0;

        int result = 100001;
        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == K) {
                result = Math.min(result, visit[now]);
                continue;
            }

            // 순간이동 -> 순간이동은 시간이 늘어나지 않으므로 가장 먼저 큐에 추가
            if (2 * now <= 100000 && visit[2 * now] > visit[now]) {
                queue.add(2 * now);
                visit[2 * now] = visit[now];
            }

            // 걷기
            if (now - 1 >= 0 && visit[now - 1] > visit[now] + 1) {
                queue.add(now - 1);
                visit[now - 1] = visit[now] + 1;
            }

            if (now + 1 <= 100000 && visit[now + 1] > visit[now] + 1) {
                queue.add(now + 1);
                visit[now + 1] = visit[now] + 1;
            }
        }

        return result;
    }
}
/*
의사코드
1. 수빈 -> 동생까지의 최단 시간을 구하는 문제이므로 BFS 를 활용한다
2. 한 번 방문했던 위치에 재방문 할 때 시간이 이전보다 같거나 느린 경우는 의미가 없으므로 재방문하지 못하도록 한다.

시간복잡도
O(K - N)
-> 최악의 경우 동생 - 수빈 의 거리를 1씩 이동할 수 있으므로 O(K - N)
*/