package SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n2042_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 변경이 일어나는 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

        long[] numbers = new long[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }

        // 세그먼트 트리 만들기
        long[] tree = new long[4 * N];
        makeTree(tree, 0, N - 1, numbers, 1);

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                // update
                long origin = numbers[b - 1];
                numbers[b - 1] = c;
                update(tree, 1, 0, N - 1, b - 1, numbers[b - 1] - origin);
            } else if (a == 2) {
                // 구간 합
                answer.append(
                        getSum(tree, 1, 0, N - 1, b - 1, (int)(c - 1))
                ).append('\n');
            }
        }

        System.out.println(answer);
    }

    static long makeTree(long[] tree, int startIdx, int endIdx, long[] numbers, int node) {
        if (startIdx == endIdx) {
            tree[node] = numbers[startIdx];
            return tree[node];
        }

        int mid = (startIdx + endIdx) / 2;
        tree[node] = makeTree(tree, startIdx, mid, numbers, 2 * node)
                + makeTree(tree, mid + 1, endIdx, numbers, 2 * node + 1);

        return tree[node];
    }

    static void update(long[] tree, int node, int startIdx, int endIdx, int numberIdx, long diff) {
        if (numberIdx < startIdx || endIdx < numberIdx) return;
        if (startIdx == endIdx) {
            tree[node] += diff;
            return;
        }

        tree[node] += diff;
        int mid = (startIdx + endIdx) / 2;
        update(tree, 2 * node, startIdx, mid, numberIdx, diff);
        update(tree, 2 * node + 1, mid + 1, endIdx, numberIdx, diff);
    }

    static long getSum(long[] tree, int node, int startIdx, int endIdx, int left, int right) {
        if (right < startIdx || endIdx < left) return 0;

        if (left <= startIdx && endIdx <= right) return tree[node];

        int mid = (startIdx + endIdx) / 2;
        return getSum(tree, 2 * node, startIdx, mid, left, right) +
                getSum(tree, 2 * node + 1, mid + 1, endIdx, left, right);
    }
}
