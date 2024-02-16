package NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n27172_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[] inGame = new boolean[1_000_001];
        int[] cards = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int end = 0;
        for (int i = 0; i < N; i++) {
            int card = Integer.parseInt(st.nextToken());
            inGame[card] = true;
            cards[i] = card;
            end = Math.max(end, card);
        }

        int[] point = new int[1_000_001];
        for (int i = 0; i < N; i++) {
            int card = cards[i];
            for (int j = card * 2; j <= end; j += card) {
                if (inGame[j]) {
                    point[card] += 1;
                    point[j] -= 1;
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int card : cards) {
            answer.append(point[card]).append(' ');
        }
        System.out.println(answer);
    }
}
/*
실제로 모든 게임을 진행하는 방식 -> O(N^2)
N 이 최대 100,000 이므로 N^2 방식은 절대 통과될 수 없다.

1 ~ 1,000,000 까지의 숫자 카드
누군가 카드를 가지고 있으면 True

7 23 8 6

7의 배수 중 True 가 있으면 7은 +1, 배수는 -1점
23의 배수 중 True 가 있으면 23은 +1, 배수는 -1점
..
6의 배수 중 True 가 있으면 6은 +1, 배수는 -1점

굳이 1,000,000까지 확인하지 않고 입력 받은 카드 중 max값 까지만 체크해도 된다

시간복잡도 : O(N * log1,000,000)
-> 최악의 경우 100,000 * log(1,000,000) 이므로 1초 통과 가능
 */