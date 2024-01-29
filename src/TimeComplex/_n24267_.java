package TimeComplex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _n24267_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        System.out.println((long)n * (n-1) * (n-2) / 6);
        System.out.println(3);
    }
}
/*
i = 1 : (n-2)(n-1)/2
i = 2 : (n-3)(n-2)/2
...
i = n-2 : 1 * 2 / 2 = 1
=> (n-2)(n-1)n / 6
 */