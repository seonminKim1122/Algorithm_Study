package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n18258_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Queue queue = new Queue(N);
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer orders = new StringTokenizer(br.readLine());
            String order = orders.nextToken();

            switch (order) {
                case "push":
                    int item = Integer.parseInt(orders.nextToken());
                    queue.push(item);
                    break;
                case "pop":
                    answer.append(queue.pop()).append("\n");
                    break;
                case "size":
                    answer.append(queue.size()).append("\n");
                    break;
                case "empty":
                    answer.append(queue.isEmpty()).append("\n");
                    break;
                case "front":
                    answer.append(queue.getFront()).append("\n");
                    break;
                default:
                    answer.append(queue.getRear()).append("\n");
            }
        }

        System.out.println(answer);
    }

    static class Queue {
        int[] arr;
        int front = 1;
        int rear = 0;

        Queue(int maxSize){
            arr = new int[maxSize + 1];
        }

        void push(int X) {
            arr[++rear] = X;
        }

        int pop() {
            if (isEmpty() == 1) {
                return -1;
            } else {
                int v = arr[front];
                arr[front++] = 0;
                return v;
            }
        }

        int size() {
            return isEmpty() == 1 ? 0 : rear - front + 1;
        }

        int isEmpty() {
            if (rear < front) {
                return 1;
            } else {
                return 0;
            }
        }

        int getFront() {
            return isEmpty() == 1 ? -1 : arr[front];
        }

        int getRear() {
            return isEmpty() == 1 ? -1 : arr[rear];
        }
    }
}
/*
시간복잡도 : O(N)
-> 큐의 모든 연산은 O(1) 의 시간복잡도를 가지므로 전체 코드의 시간복잡도는 명령의 갯수와 비례
 */