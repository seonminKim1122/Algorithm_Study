import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _n18114_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] W = new int[N];
        for (int i = 0; i < N; i++) {
            W[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(W);

        if (solve1(W, C) == 1) {
            System.out.println(1);
        } else if (solve2(W, C) == 1) {
            System.out.println(1);
        } else {
            System.out.println(solve3(W, C));
        }
    }

    static int solve1(int[] W, int C) {
        int l = 0;
        int e = W.length - 1;

        while (l <= e) {
            int mid = (l + e) / 2;

            if (W[mid] < C) {
                l = mid + 1;
            } else if (W[mid] > C) {
                e = mid - 1;
            } else {
                return 1;
            }
        }

        return 0;
    }

    static int solve2(int[] W, int C) {
        int N = W.length;

        for (int i = 0; i < N - 1; i++) {
            int l = i + 1;
            int e = N - 1;

            while (l <= e) {
                int mid = (l + e) / 2;

                if (W[mid] + W[i] < C) {
                    l = mid + 1;
                } else if (W[mid] + W[i] > C) {
                    e = mid - 1;
                } else {
                    return 1;
                }
            }
        }

        return 0;
    }

    static int solve3(int[] W, int C) {
        int N = W.length;
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                int l = j + 1;
                int e = N - 1;


                while (l <= e) {
                    int mid = (l + e) / 2;
                    if (W[mid] + W[i] + W[j] < C) {
                        l = mid + 1;
                    } else if (W[mid] + W[i] + W[j] > C) {
                        e = mid - 1;
                    } else {
                        return 1;
                    }
                }
            }
        }

        return 0;
    }
}