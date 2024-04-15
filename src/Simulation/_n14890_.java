package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n14890_ {

    static int result;
    static int N;
    static int L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;

        for (int n = 0; n < N; n++) {
            checkRow(n);
            checkColumn(n);
        }

        System.out.println(result);
    }

    static void checkRow(int row) {
        int col = 0;
        boolean[] isInstalled = new boolean[N];
        boolean isBridge = true;
        while (col < N - 1) {
            boolean canInstall = true;
            if (map[row][col] < map[row][col + 1]) {
                // row, col + 1 왼쪽으로 경사로 설치
                for (int l = 0; l < L; l++) {
                    if (col - l < 0 || isInstalled[col - l] || (map[row][col - l] != map[row][col + 1] - 1)) {
                        canInstall = false;
                        break;
                    }
                }
                if (!canInstall) {
                    isBridge = false;
                    break;
                }
                for (int l = 0; l < L; l++) {
                    isInstalled[col - l] = true;
                }
                col++;
            } else if (map[row][col] > map[row][col + 1]) {
                for (int l = 0; l < L; l++) {
                    if (col + 1 + l >= N || isInstalled[col + 1 + l] || (map[row][col] - 1 != map[row][col + 1 + l])) {
                        canInstall = false;
                        break;
                    }
                }
                if (!canInstall) {
                    isBridge = false;
                    break;
                }
                for (int l = 0; l < L; l++) {
                    isInstalled[col + 1 + l] = true;
                }
                col += L;
            } else{
                col++;
            }
        }

        if (isBridge) result++;
    }

    static void checkColumn(int col) {
        int row = 0;
        boolean[] isInstalled = new boolean[N];
        boolean isBridge = true;
        while (row < N - 1) {
            boolean canInstall = true;
            if (map[row][col] < map[row + 1][col]) {
                // row + 1, col 위로 경사로 설치
                for (int l = 0; l < L; l++) {
                    if (row - l < 0 || isInstalled[row - l] || (map[row - l][col] != map[row + 1][col] - 1)) {
                        canInstall = false;
                        break;
                    }
                }
                if (!canInstall) {
                    isBridge = false;
                    break;
                }
                for (int l = 0; l < L; l++) {
                    isInstalled[row - l] = true;
                }
                row++;
            } else if (map[row][col] > map[row + 1][col]) {
                // row, col 아래로 경사로 설치
                for (int l = 0; l < L; l++) {
                    if (row + 1 + l >= N || isInstalled[row + 1 + l] || (map[row][col] - 1 != map[row + 1 + l][col])) {
                        canInstall = false;
                        break;
                    }
                }
                if (!canInstall) {
                    isBridge = false;
                    break;
                }
                for (int l = 0; l < L; l++) {
                    isInstalled[row + 1 + l] = true;
                }
                row += L;
            } else {
                row++;
            }
        }

        if (isBridge) result++;
    }
}
