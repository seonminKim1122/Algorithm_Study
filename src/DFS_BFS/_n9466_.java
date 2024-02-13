package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n9466_ {

    static int[] select;
    static boolean[] checked;
    static boolean[] visit;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            select = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                select[i] = Integer.parseInt(st.nextToken());
            }

            result = N;
            checked = new boolean[N + 1];
            visit = new boolean[N + 1];
            for (int i = 1; i <= N; i++) {
                if (!checked[i]) {
                    visit[i] = true;
                    dfs(i);
                    visit[i] = false;
                    checked[i] = true;
                }
            }

            answer.append(result).append('\n');
        }

        System.out.println(answer);
    }

    static void dfs(int person) {
        int next = select[person];
        // 이미 체크된 곳이므로 팀을 꾸릴 수 없음
        if (checked[next]) return;

        if (visit[next]) { // next 를 시작으로 하는 서브 사이클 발생
            result -= 1;
            while (next != person) {
                next = select[next];
                result -= 1;
            }
            return;
        }

        visit[next] = true;
        dfs(next);
        visit[next] = false;
        checked[next] = true;
    }
}
/*
의사코드
1. 이미 팀에 속한 인원의 경우 넘어가고 그렇지 않은 경우 DFS 를 수행한다.
2. DFS 를 수행하며 '사이클'이 발생한 경우 사이클에 있는 사람들을 하나의 '팀'으로 간주한다.
-> 갔던 곳을 또 가게 되면 사이클!
-> 만약 이미 팀을 구성할 수 있다/없다 가 판별이 난 사람과 연결이 된다면 팀을 이룰 수 없다는 의미
-> checked 배열을 이용해 판별이 난 사람을 구분한다.
3. '사이클'이 발생하지 않았는데 추가 탐색이 불가능한 경우 팀을 이룰 수 없는 케이스로 간주한다.

1 -> 3 -> 3 : 3 -> 3 서브 사이클 발생했으니 3은 팀 결성, 지나온 경로 check true 처리
2 -> 1 -> 3 -> 3 : 2 -> 1 때 1은 이미 결성 실패한 사람이므로 2 도 실패, check true 처리
3 -> 3 : 이미 check 했으니 넘어감
4 -> 7 -> 6 -> 4 : 사이클이므로 팀 결성, 지나온 경로 check true 처리
..
*/