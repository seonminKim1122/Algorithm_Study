package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _n28279_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder answer = new StringBuilder();
        Deque<Integer> deque = new LinkedList<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer orders = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(orders.nextToken());

            switch (order) {
                case 1:
                    deque.addFirst(Integer.parseInt(orders.nextToken()));
                    break;
                case 2:
                    deque.addLast(Integer.parseInt(orders.nextToken()));
                    break;
                case 3:
                    answer.append(deque.isEmpty() ? -1 : deque.pollFirst()).append("\n");
                    break;
                case 4:
                    answer.append(deque.isEmpty() ? -1 : deque.pollLast()).append("\n");
                    break;
                case 5:
                    answer.append(deque.size()).append("\n");
                    break;
                case 6:
                    answer.append(deque.isEmpty() ? 1 : 0).append("\n");
                    break;
                case 7:
                    answer.append(deque.isEmpty() ? -1 : deque.peekFirst()).append("\n");
                    break;
                default:
                    answer.append(deque.isEmpty() ? -1 : deque.peekLast()).append("\n");
            }
        }

        System.out.println(answer);
    }
}
/*
시간복잡도 : O(N)
-> 덱 자료구조의 경우 삽입, 삭제 연산은 O(1) 이므로 명령의 갯수가 전체 코드의 시간복잡도를 결정
 */