package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class _n2164_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        while (queue.size() > 1) {
            // 제일 위에 있는 카드를 바닥에 버린다.
            queue.poll();

            // 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.
            // 단, 이 때 이미 큐의 사이즈가 1일 수 있으므로 1이 아닌 경우만 해당 연산을 수행한다
            if (queue.size() > 1) {
                queue.add(queue.poll());
            }
        }

        System.out.println(queue.poll());
    }
}

/*
시간복잡도 : O(N)
이유 : queue 의 size 가 1 이 될 때까지 하는 연산(poll, add) 들이 전부 O(1) 연산들이기 때문에
while 문이 몇 번 반복되는지가 시간복잡도 결정
 */
