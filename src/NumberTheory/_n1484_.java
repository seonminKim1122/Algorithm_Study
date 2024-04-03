package NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _n1484_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int sqrt = (int)Math.sqrt(G);

        List<Integer> answers = new ArrayList<>();
        for (int i = sqrt; i >= 1; i--) {
            if (G % i == 0 && (G / i) != i) {
                int temp = (G / i) + i;
                if (temp % 2 != 0) continue;
                answers.add(temp / 2);
            }
        }

        if (answers.size() == 0) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < answers.size(); i++) {
                System.out.println(answers.get(i));
            }
        }
    }
}
/*
G = N**2 - P**2

G = (N + P)*(N - P)

i : 1 ~ root(G)

G % i == 0 이 되면 G / i = N + P, i = N - P
(G / i) + i = 2 * N

((G / i) + i) / 2 => N


*/
