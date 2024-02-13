package DisjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n1717_ {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (operation == 0) {
                // union
                union(a, b);
            } else {
                if (find(a) == find(b)) {
                    answer.append("YES").append('\n');
                } else {
                    answer.append("NO").append('\n');
                }
            }
        }

        System.out.println(answer);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA != parentB) {
            parent[parentB] = parentA;
        }
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        parent[a] = find(parent[a]);
        return parent[a];
    }
}
/*
의사코드
1. 합집합 연산을 해야할 차례 때는 uniㅐㅜ 연산을 하고
2. 그렇지 않은 경우 find 연산으로 각 번호가 속한 집합의 대장 노드를 비교해서 같으면 같은 집합이니 "YES" 다르면 "NO"
 */