package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class _n17219_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, String> cheatSheet = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String url = st.nextToken();
            String password = st.nextToken();

            cheatSheet.put(url, password);
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String url = br.readLine();
            answer.append(cheatSheet.get(url)).append("\n");
        }

        System.out.println(answer);
    }
}
/*
시간복잡도 : O(N+M)
HashMap 은 해시 기반이므로 삽입, 탐색 연산이 O(1)
 */