package SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _11505_ {

    static int DIV = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // update 횟수
        int K = Integer.parseInt(st.nextToken()); // query 횟수

        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }
        long[] tree = new long[4 * N];
        makeTree(tree, 1, 0, N - 1, numbers);

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                int before = numbers[b - 1];
                numbers[b - 1] = c;
                update(tree, 1, 0, N - 1, numbers, b - 1);
            } else if (a == 2) {
                long result = query(tree, 1, 0, N - 1, b - 1, c - 1);
                answer.append(result).append('\n');
            }
        }

        System.out.println(answer);
    }

    static long makeTree(long[] tree, int node, int startIdx, int endIdx, int[] numbers) {
        if (startIdx == endIdx) {
            tree[node] = numbers[startIdx];
            return tree[node];
        }

        int mid = (startIdx + endIdx) / 2;
        tree[node] = (makeTree(tree, 2 * node, startIdx, mid, numbers) *
                makeTree(tree, 2 * node + 1, mid + 1, endIdx, numbers)) % DIV;
        return tree[node];
    }

    static long update(long[] tree, int node, int startIdx, int endIdx, int[] number, int numberIdx) {
        if (numberIdx < startIdx || endIdx < numberIdx) return tree[node];

        if (startIdx == endIdx) {
            tree[node] = number[numberIdx];
            return tree[node];
        }

        int mid = (startIdx + endIdx) / 2;
        tree[node] = (update(tree, 2 * node, startIdx, mid, number, numberIdx) *
                update(tree, 2 * node + 1, mid + 1, endIdx, number, numberIdx)) % DIV;
        return tree[node];
    }

    static long query(long[] tree, int node, int startIdx, int endIdx, int left, int right) {
        if (right < startIdx || endIdx < left) return 1;
        if (left <= startIdx && endIdx <= right) return tree[node];

        int mid = (startIdx + endIdx) / 2;
        return (query(tree, 2 * node, startIdx, mid, left, right) *
                query(tree, 2 * node + 1, mid + 1, endIdx, left, right)) % DIV;
    }
}
