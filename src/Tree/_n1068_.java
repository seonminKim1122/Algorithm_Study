package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n1068_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] parents = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            parents[i] = Integer.parseInt(st.nextToken());
        }

        int removeNode = Integer.parseInt(br.readLine());
        parents[removeNode] = -2;
        solve(parents, removeNode);

        int[] numOfChild = new int[N];
        for (int i = 0; i < N; i++) {
            if (parents[i] < 0) continue;
            numOfChild[parents[i]]++;
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            // parents[i] == -2 이면 제거된 노드!!
            if (parents[i] != -2 && numOfChild[i] == 0) result++;
        }

        System.out.println(result);
    }

    static void solve(int[] parents, int node) {
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == node) {
                parents[i] = -2;
                solve(parents, i);
            }
        }
    }
}
