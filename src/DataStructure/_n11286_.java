package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class _n11286_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>( // Comparator 를 문제에서 요구한대로 지정
            (i1, i2) -> Math.abs(i1) - Math.abs(i2) == 0 ? i1 - i2 : Math.abs(i1) - Math.abs(i2)
        );

        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x != 0) {
                priorityQueue.add(x);
            } else {
                if (priorityQueue.isEmpty()) {
                    answer.append(0).append("\n");
                } else {
                    answer.append(priorityQueue.poll()).append("\n");
                }
            }
        }

        System.out.println(answer);
    }
}
/*
시간복잡도 : O(NlogN)
-> 매번 삽입 또는 삭제 연산이 발생하는데 힙의 삽입/삭제는 O(logN)
 */