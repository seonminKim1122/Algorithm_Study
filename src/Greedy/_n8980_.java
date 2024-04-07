package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _n8980_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 마을 수
        int C = Integer.parseInt(st.nextToken()); // 트럭 용량

        int M = Integer.parseInt(br.readLine()); // 보낼 정보

        List<Info> infos = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int box = Integer.parseInt(st.nextToken());

            Info info = new Info(from, to, box);
            infos.add(info);
        }

        Collections.sort(infos);

        int[] available = new int[N + 1]; // truck[i] -> 트럭에 있는 박스 중 i 마을이 목적지인 박스 수
        Arrays.fill(available, C);

        int result = 0;
        for (Info info : infos) {
            int min = C;
            for (int i = info.from; i < info.to; i++) {
                min = Math.min(min, available[i]);
            }

            min = Math.min(min, info.box);
            result += min;
            for (int i = info.from; i < info.to; i++) {
                available[i] -= min;
            }
        }

        System.out.println(result);
    }

    static class Info implements Comparable<Info> {
        int from;
        int to;
        int box;

        Info (int from, int to, int box) {
            this.from = from;
            this.to = to;
            this.box = box;
        }

        @Override
        public int compareTo(Info other) {
            return this.to - other.to;
        }
    }
}
/*
오름차순 기준
- 받는 마을 기준 오름차순 정렬!

최대한 실어서 옮기기!
1 2 10
1 3 20
2 3 10
1 4 30
2 4 20
3 4 20
----
1 2 30
3 4 40
2 5 70
1 6 40
5 6 60

from to box 면

available[from] ~ available[to - 1] 를 탐색하며 box 중 얼마를 더 실을 수 있는지 체크
이 중 최소치 vs box 의 min 값이 그 정답!

result += min
available[from] ~ available[to - 1] 을 -= min 해주기!
*/