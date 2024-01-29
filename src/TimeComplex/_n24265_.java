package TimeComplex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _n24265_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        System.out.println((long) n * (n - 1) / 2);
        System.out.println(2);
    }
}
/*
1 <= i <= n-1
i + 1 <= j <= n

2 <= <= n
3 <= <= n
...
n <= <= n

1 + 2 + 3 + 4 + ... + n-1
=> (n-1)*n / 2
 */
