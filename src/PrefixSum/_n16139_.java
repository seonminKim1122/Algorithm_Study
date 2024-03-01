package PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n16139_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] S = br.readLine().toCharArray();

        int[][] cumulateSum = new int[26][S.length + 1];
        for (int i = 1; i <= S.length; i++) {
            char alphabet = S[i - 1];
            for (int j = 0; j < 26; j++) {
                cumulateSum[j][i] = cumulateSum[j][i - 1];
            }
            cumulateSum[alphabet - 'a'][i] = cumulateSum[alphabet - 'a'][i - 1] + 1;
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char alpha = st.nextToken().charAt(0);
            int i = Integer.parseInt(st.nextToken()) + 1;
            int j = Integer.parseInt(st.nextToken()) + 1;

            int temp = cumulateSum[alpha - 'a'][j] - cumulateSum[alpha - 'a'][i - 1];
            answer.append(temp).append('\n');
        }

        System.out.println(answer);
    }
}
/*
누적합 배열
-> [alphabet][i]
-> 주어진 문자열 S의 i번 index 까지 고려했을 때 각 alphabet 의 등장 횟수를 누적!

  0 1 2 3 4 5 ... N
a 0 0 0
b 0 0 0
c 0 0 0
d 0 0 0
e 0 0 1
. 0 0 0
s 0 1 0
. 0 0 0
z 0 0 0
 */
