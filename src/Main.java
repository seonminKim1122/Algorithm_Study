import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 타겟값

        int N = Integer.parseInt(br.readLine()); // A size
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine()); // B size
        st = new StringTokenizer(br.readLine());
        int[] B = new int[M];
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // A 의 누적합 배열
        int[] Sa = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            Sa[i] = Sa[i - 1] + A[i - 1];
        }
        // B 의 누적합 배열
        int[] Sb = new int[M + 1];
        for (int i = 1; i <= M; i++) {
            Sb[i] = Sb[i - 1] + B[i - 1];
        }
        
        // 누적합 배열 써서 뭔가 해보자
        /*
        시간 줄이기(제한시간 2초)
        1. 브루트 포스 -> 시간 초과
        2. Two pointer? -> 입력값이 정수라서 end 포인터를 키우는 것 = 합을 키우는 것이라는 보장이 없음
        3. Binary Search? -> 입력값이 정수라서 뒤에 있는 게 더 작을 수도 있음.. 
         */
        int result = 0;
        for (int ia = 1; ia <= N; ia++) {
            for (int ja = ia; ja <= N; ja++) {
                int tempSum = Sa[ja] - Sa[ia - 1];
                
                // 이 부분에서 시간 줄일 수 있는 방법 고민해보기
                for (int ib = 1; ib <= M; ib++) {
                    for (int jb = ib; jb <= M; jb++) {
                        int S = tempSum + (Sb[jb] - Sb[ib - 1]);

                        if (S == T) {
                            System.out.println(ia + " " + ja + " " + ib + " " + jb);
                            result++;
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }
}
