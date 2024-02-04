package DFS_BFS.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n9466_ {


    static int[] select;
    static boolean[] visit;
    static boolean[] checked;
    static int hasTeam;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer= new StringBuilder();
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

            select = new int[n + 1];
            visit = new boolean[n + 1];
            checked = new boolean[n + 1];
            hasTeam = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                select[j] = Integer.parseInt(st.nextToken());
            }

            for (int student = 1; student <= n; student++) {
                if (!visit[student]) {
                    dfs(student);
                }
            }

            answer.append(n - hasTeam).append('\n');
        }

        System.out.println(answer);
    }

    static void dfs(int student) {
        visit[student] = true;

        int next = select[student];
        if (!visit[next]) {
            dfs(next);
        } else { // cycle! or 이미 팀 구성 여부가 결정된 곳 방문
            if (!checked[next]) { // checked[next]가 false 면 next 부터 사이클을 의미
                hasTeam++;
                while (next != student) {
                    next = select[next];
                    hasTeam++;
                }
            }
        }
        checked[student] = true;
    }
}
/*
의사코드
1. 팀이 이뤄지는 경우는 X 에서 출발해서 X 로 돌아오는 사이클이 있을 때
2. 임의의 학생에서 출발하면 DFS 탐색이 멈추는 경우는 한 번 방문한 곳을 재방문할 때 뿐이다.
-> 사이클이 발생했다 -> 해당 노드부터 사이클을 1번 탐색하며 팀 구성 인원 수를 센다
-> 팀 구성여부가 이미 결정된 곳을 방문했다 -> 현재 학생이 팀을 구성할 수 없다는 의미
3. 카운팅 된 팀 구성 인원 수를 전체 인원 수에서 빼면 팀에 속하지 못한 학생 수가 나온다.
 */