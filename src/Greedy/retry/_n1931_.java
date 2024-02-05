package Greedy.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class _n1931_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Meeting> meetings = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings.add(new Meeting(start, end));
        }
        // 끝나는 시간 기준 오름차순 정렬
        Collections.sort(meetings);

        int beforeEnd = -1;
        int result = 0;
        for (Meeting meeting : meetings) {
            if (meeting.start >= beforeEnd) {
                result++;
                beforeEnd = meeting.end;
            }
        }

        System.out.println(result);
    }

    static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        Meeting (int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            // 끝나는 시간 기준 오름차순 정렬
            return this.end - o.end == 0 ? this.start - o.start : this.end - o.end;
        }
    }
}
/*
의사코드
1. 회의실을 최대한 많이 사용하기 위해서는 가능한 회의 중 끝나는 시간이 이른 회의부터 진행해야 한다.
2. ex) 0 ~ 6, 1 ~ 4 회의가 있을 때 1 ~ 4회의를 진행하면 4 ~ 5 에 시작하는 회의를 진행할 수 있지만
0 ~ 6 회의를 진행하면 불가능하다.
3. 단 시작 시간과 끝나는 시간이 동일한 회의도 존재할 수 있으므로 끝나는 시간이 같은 경우
시작 시간이 빠른 회의를 먼저 진행하도록 해야 한다.

시간복잡도
O(NlogN + N)
-> 입력된 데이터를 종료 시간이 빠른 순으로 정렬하는데 NlogN
-> 정렬된 데이터에 대해서 가능한 회의 숫자를 세는데 N
 */