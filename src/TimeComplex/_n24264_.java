package TimeComplex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _n24264_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        System.out.println((long)n * n);
        System.out.println(2);
    }
}
/*
시간복잡도 : O(N^2)

실제로 메서드를 구현하고 count 변수를 통해 코드 호출 횟수를 세는 방식은
진짜 O(N^2) 만큼 소요되므로 n = 500,000 일 때는 엄청난 횟수의 연산을 필요로 한다.

이렇게 되면 시간 제한인 1초 안에 통과할 수 없으므로 실제 구현은 하지 않는다.
 */