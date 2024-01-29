package TimeComplex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n24313_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a1 = Integer.parseInt(st.nextToken());
        int a0 = Integer.parseInt(st.nextToken());

        int c = Integer.parseInt(br.readLine());
        int n0 = Integer.parseInt(br.readLine());

        if ((c - a1 >= 0) && (c - a1) * n0 >= a0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
/*
n >= n0 일 때 a1 * n + a0 <= cn 을 만족하는지를 판단하는 문제

a1 * n + a0 <= cn
-> a0 <= (c - a1) * n

1. c - a1 >= 0 일 때
n = n0 일 때만 만족하면 나머지 케이스는 무조건 만족
a0 <= (c - a1) * n0 만 확인

2. c - a1 < 0 일 때
-100 <= a0 <= 100 인데 n 은 무한히 커질 수 있으므로
무조건 식을 만족하지 않는 케이스 존재하게 됨
 */