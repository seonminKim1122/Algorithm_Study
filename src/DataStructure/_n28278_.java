package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n28278_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Stack stack = new Stack(N);

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer orders = new StringTokenizer(br.readLine());

            int orderNum = Integer.parseInt(orders.nextToken());

            switch (orderNum) {
                case 1:
                    stack.push(Integer.parseInt(orders.nextToken()));
                    break;
                case 2:
                    answer.append(stack.pop()).append("\n");
                    break;
                case 3:
                    answer.append(stack.size()).append("\n");
                    break;
                case 4:
                    answer.append(stack.isEmpty()).append("\n");
                    break;
                default:
                    answer.append(stack.peek()).append("\n");
            }
        }

        System.out.println(answer);
    }

    static class Stack {
        int[] arr;
        int top = 0;

        Stack(int maxSize) {
            arr = new int[maxSize + 1];
        }

        void push(int x) {
            arr[++top] = x;
        }

        int pop() {
            if (this.isEmpty() == 1) {
                return -1;
            } else {
                int v = arr[top];
                arr[top--] = 0;

                return v;
            }
        }

        int size() {
            return top;
        }

        int isEmpty() {
            return top == 0 ? 1 : 0;
        }

        int peek() {
            return this.isEmpty() == 1 ? -1 : arr[top];
        }
    }
}
/*
시간복잡도 : O(N)
이유 : 스택의 모든 연산이 O(1) 인 연산이므로 연산의 횟수가 곧 시간복잡도
 */