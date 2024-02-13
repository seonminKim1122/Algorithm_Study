package DisjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n1976_ {

    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int node1 = 1; node1 <= N; node1++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int node2 = 1; node2 <= N; node2++) {
                int connect = Integer.parseInt(st.nextToken());
                if (connect == 0) continue;
                // union
                union(node1, node2);
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int before = Integer.parseInt(st.nextToken());

        for (int i = 1; i < M; i++) {
            int node = Integer.parseInt(st.nextToken());
            if (find(before) != find(node)) {
                System.out.println("NO");
                return;
            }
            before = node;
        }

        System.out.println("YES");
    }

    static void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 != root2) {
            parent[root2] = root1;
        }
    }

    static int find(int node) {
        if (parent[node] == node) return node;
        parent[node] = find(parent[node]);
        return parent[node];
    }
}
/*
의사코드
1. 중간에 다른 도시를 경유하는 것이 가능하므로 결국 입력받은 여행지들이 같은 집합에 속하는지를 판단하면 된다.
2. Disjoint Set 을 구하기 위한 UNION-FIND 방식을 이용한다!
 */