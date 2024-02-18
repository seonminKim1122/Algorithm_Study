import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _n16637_ {

    static int N;
    static char[] formula;
    static boolean[] checked;
    static boolean[] visit;

    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 데이터 입력 받고 숫자, 연산자 구분
        N = Integer.parseInt(br.readLine());
        formula = br.readLine().toCharArray();

        checked = new boolean[N / 2];
        visit = new boolean[N / 2];

        dfs(0);
        System.out.println(max);
    }

    static void dfs(int start) {
        // 현재 괄호 쳐진 상태로 계산
        int temp = calculate(makePostOrder());
        if (max < temp) {
            max = temp;
        }

        // 더 이상 괄호칠 수 있는 연산자가 없으면 return
        int visitCnt = 0;
        for (int i = 0; i < N / 2; i++) {
            if (visit[i]) visitCnt++;
        }
        if (visitCnt == N / 2) return;
        
        // checked 배열 true, false 처리
        // visit 배열 true, false 처리
        // visit[i] 를 true 처리하면 i - 1, i + 1 도 같이 됨.
        for (int i = start; i < N / 2; i++) {
            if (visit[i]) continue;

            checked[i] = true;
            boolean flag1 = false;
            boolean flag2 = false;
            if (i - 1 >= 0 && !visit[i - 1]) {
                visit[i - 1] = true;
                flag1 = true;
            }
            visit[i] = true;
            if (i + 1 < N / 2 && !visit[i + 1]) {
                visit[i + 1] = true;
                flag2 = true;
            }

            dfs(i + 2);

            checked[i] = false;
            if (flag1) visit[i - 1] = false;
            visit[i] = false;
            if (flag2) visit[i + 1] = false;
        }
    }

    static int calculate(List<Character> postOrder) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < postOrder.size(); i++) {
            char now = postOrder.get(i);
            if ('0' <= now && now <= '9') { // 숫자
                stack.add(now - '0');
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.add(operate(num1, num2, now));
            }
        }

        return stack.pop();
    }

    static List<Character> makePostOrder() {
        Stack<char[]> operatorStack = new Stack<>();
        List<Character> postOrder = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) { // 숫자
                postOrder.add(formula[i]);
            } else { // 연산자
                char operator = formula[i];
                char priority = '0';
                if (checked[i / 2]) priority = '1';

                while (!operatorStack.isEmpty()) {
                    char[] before = operatorStack.pop();
                    if (before[1] >= priority) {
                        postOrder.add(before[0]);
                    } else {
                        operatorStack.add(before);
                        break;
                    }
                }
                operatorStack.add(new char[]{operator, priority});
            }
        }

        while (!operatorStack.isEmpty()) {
            postOrder.add(operatorStack.pop()[0]);
        }

        return postOrder;
    }

    static int operate(int num1, int num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            default: // *
                return num1 * num2;
        }
    }
}
/*
step1. 문자열로 구성된 수식에 괄호를 추가해서 계산하기 구현
-> 괄호를 안 쓴 연산자는 우선순위 0
-> 괄호를 쓴 연산자는 우선순위 1 로 둬서 후위표기식으로 바꾸기??

step2. 연산자 중 괄호를 사용할 곳과 아닌 곳 선택하기 구현
-> i번째 연산자에 괄호를 사용하기로 선택했다면 i - 1번째와 i + 1번째는 괄호를 사용할 수 없다.(괄호가 겹쳐버림)

 */