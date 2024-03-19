package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n9934_ {

    static int[] inOrder;
    static int inOrderIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        int size = (int)Math.pow(2, K) - 1;
        int[] tree = new int[size + 1];

        inOrder = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }
        inOrderIdx = 0;
        fillTree(1, tree);
        
        // 각 level 에 맞춰서 출력
        StringBuilder answer = new StringBuilder();
        int level = 1;
        while (level <= K) {
            int from = (int)Math.pow(2, level - 1);
            int to = (int)Math.pow(2, level);
            for (int i = from; i < to; i++) {
                answer.append(tree[i]).append(' ');
            }
            answer.append('\n');
            level++;
        }

        System.out.println(answer);
    }

    static void fillTree(int node, int[] tree) {
        if (node >= tree.length) return;

        fillTree(2 * node, tree);
        // 자기 자신 채우기
        tree[node] = inOrder[inOrderIdx++];
        fillTree(2 * node + 1, tree);
    }
}
/*
문제에서는 트리를 중위순회한 결과가 주어짐
중위순회한 결과를 바탕으로 트리를 구성
 */