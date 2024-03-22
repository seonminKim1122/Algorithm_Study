package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _n23559_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int max = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken()); // 5000원짜리 메뉴의 맛
            int B = Integer.parseInt(st.nextToken()); // 1000원짜리 메뉴의 맛

            max += B;
            int diff = A - B;
            pq.add(diff);
        }

        int price = 1000 * N;

        while (price <= K - 4000 && !pq.isEmpty()) {
            int diff = pq.poll();
            if (diff <= 0) continue;
            max += diff;
            price += 4000;
        }

        System.out.println(max);
    }
}
/*
일단 1000원짜리를 전부 다 먹는 다고 가정
그 중에 5000원짜리로 교체하되 (그 날 1000원짜리와 맛의 차이가 제일 큰 거로 선택)
 */