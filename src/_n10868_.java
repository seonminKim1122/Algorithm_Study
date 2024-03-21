import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _n10868_ {

    static int INF = 1_000_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int[] tree = new int[4 * N];
        makeTree(tree, numbers, 1, 0, N - 1);

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            answer.append(query(tree, 1, 0, N - 1, a, b));
            answer.append('\n');
        }

        System.out.println(answer);
    }

    static int makeTree(int[] tree, int[] numbers, int node, int startIdx, int endIdx) {
        if (startIdx == endIdx) {
            tree[node] = numbers[startIdx];
            return tree[node];
        }

        int mid = (startIdx + endIdx) / 2;
        tree[node] = Math.min(makeTree(tree, numbers, 2 * node, startIdx, mid),
                makeTree(tree, numbers, 2 * node + 1, mid + 1, endIdx));

        return tree[node];
    }

    static int query(int[] tree, int node, int startIdx, int endIdx, int left, int right) {
        if (right < startIdx || endIdx < left) return INF;

        if (left <= startIdx && endIdx <= right) return tree[node];
        int mid = (startIdx + endIdx) / 2;
        return Math.min(query(tree, 2 * node, startIdx, mid, left, right),
                query(tree, 2 * node + 1, mid + 1, endIdx, left, right));
    }
}
