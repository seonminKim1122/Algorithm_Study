package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class _n2831_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Integer> minusMen = new ArrayList<>();
        List<Integer> plusMen = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());
            if (height < 0) {
                minusMen.add(-height);
            } else {
                plusMen.add(height);
            }
        }

        List<Integer> minusWomen = new ArrayList<>();
        List<Integer> plusWomen = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());
            if (height < 0) {
                minusWomen.add(-height);
            } else {
                plusWomen.add(height);
            }
        }

        // sort
        Collections.sort(minusMen);
        Collections.sort(plusMen);
        Collections.sort(minusWomen);
        Collections.sort(plusWomen);

        // minusMen - plusWomen
        int answer = 0;

        int manPointer = 0;
        int womanPointer = 0;
        while (manPointer < minusMen.size() && womanPointer < plusWomen.size()) {
            if (minusMen.get(manPointer) > plusWomen.get(womanPointer)) {
                manPointer++;
                womanPointer++;
                answer++;
            } else {
                manPointer++;
            }
        }

        // minusWoman - plusMan
        manPointer = 0;
        womanPointer = 0;
        while (manPointer < plusMen.size() && womanPointer < minusWomen.size()) {
            if (minusWomen.get(womanPointer) > plusMen.get(manPointer)) {
                manPointer++;
                womanPointer++;
                answer++;
            } else {
                womanPointer++;
            }
        }

        System.out.println(answer);
    }
}

/*
O(N)
-1800 -2000
1700 1900

(-1800, 1700)
(-2000, 1900)
 */