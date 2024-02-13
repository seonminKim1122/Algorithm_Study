package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _n1202_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Jewelry> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            Jewelry jewelry = new Jewelry(M, V);
            pq.add(jewelry);
        }

        int[] bags = new int[K];
        boolean[] used = new boolean[K];
        for (int i = 0; i < K; i++) {
            int bagSize = Integer.parseInt(br.readLine());
            bags[i] = bagSize;
        }
        Arrays.sort(bags);

        // 가장 비싼 보석부터 최대한 작은 가방에 담기!
        int result = 0;
        while (!pq.isEmpty()) {
            Jewelry jewelry = pq.poll();

            for (int i = 0; i < K; i++) { // 여기서 시간초과 문제 발생 -> O(N) 보다 더 좋은 방법으로 가방을 정해야 한다. Binary 와 관련된 방식을 고민해보자
                if (used[i]) continue;
                if (bags[i] >= jewelry.weight) {
                    used[i] = true;
                    result += jewelry.value;
                    break;
                }
            }
        }

        System.out.println(result);
    }

    static class Jewelry implements Comparable<Jewelry> {
        int weight;
        int value;

        Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewelry o) {
            return o.value - this.value;
        }
    }
}
