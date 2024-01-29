package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class _n1874_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        StringBuilder answer = new StringBuilder();

        int num = 1;
        for (int i = 0; i < n; i++) {
            int need = Integer.parseInt(br.readLine());

            if (num > need) { // 이미 필요한 값을 스택에 넣은 적이 있다
                boolean flag = false;
                while (!stack.isEmpty()) {
                    int top = stack.pop();
                    answer.append("-\n");
                    if (top == need) {
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    answer.setLength(0);
                    answer.append("NO");
                    break;
                }
            } else { // 아직 넣지 않았다
                while (num <= need) {
                    stack.push(num++);
                    answer.append("+\n");
                }
                stack.pop();
                answer.append("-\n");
            }
        }

        System.out.println(answer);
    }
}
/*
필요한 값 : m
현재 넣어야 하는 값 : n

m > n 이면 n == m 될 때까지 push
m < n 이면 stack 에서 pop 하면서 해당 원소 찾기

이미 다른 요소를 뽑는 과정에서 pop 이 되버렸으면 해당 원소를 찾을 수 없을 것이고
이 경우 stack.isEmpty() -> true 가 될 때 까지 flag 값이 False

시간복잡도 : O(N)
이유 : for 문 내부에서는 O(1) 인 연산들이 여러 번 수행되는 것일 뿐임
 */