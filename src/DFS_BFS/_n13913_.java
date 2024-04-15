package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _n13913_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 수빈이 위치
        int K = Integer.parseInt(st.nextToken()); // 동생 위치

        boolean[] visited = new boolean[100001];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{N, 0});
        visited[N] = true;

        int[] before = new int[100001];
        Arrays.fill(before, -1);

        int[] d = {-1, 1};
        int result = -1;
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();

            int now = temp[0];
            int seconds = temp[1];

            if (now == K) {
                result = seconds;
                break;
            }

            for (int k = 0; k < 2; k++) {
                int next = now + d[k];
                if (next < 0 || next > 100000) continue;
                if (visited[next]) continue;

                visited[next] = true;
                before[next] = now;
                queue.add(new int[]{next, seconds + 1});
            }

            int next = 2 * now;
            if (next > 100000) continue;
            if (visited[next]) continue;

            visited[next] = true;
            before[next] = now;
            queue.add(new int[]{next, seconds + 1});
        }


        StringBuilder path = new StringBuilder();
        path.append(result).append('\n');

        Stack<Integer> stack = new Stack<>();
        int position = K;
        while (position != -1) {
            stack.add(position);
            position = before[position];
        }

        while (!stack.isEmpty()) {
            path.append(stack.pop()).append(' ');
        }

        System.out.println(path);
    }
}
