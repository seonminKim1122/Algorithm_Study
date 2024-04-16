package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n20207_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] plans = new int[367];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            for (int j = S; j <= E; j++) {
                plans[j]++;
            }
        }

        int i = 1;
        int result = 0;
        while (i < 367) {

            if (plans[i] != 0) {
                int height = plans[i];
                int j = i;
                while (j < 367 && plans[j] != 0) {
                    j++;
                    height = Math.max(height, plans[j]);
                }
                result += (j - i) * height;

                i = j;
            } else {
                i++;
            }
        }
        System.out.println(result);
    }
}
/*
S ~ E 를 인덱스로 하는 칸을 +1

앞에서부터 0 이 아닌 곳을 만나면 다음 0이 아닌 곳까지의 길이를 파악하고 그 중 가장 큰 값을 높이로 해서 직사각형 넓이 계산
 */