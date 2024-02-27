package ReadyForA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_4013 {

    static int[][] magnets = new int[4][8];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            int K = Integer.parseInt(br.readLine());
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    magnets[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < K; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int magnetIdx = Integer.parseInt(st.nextToken()) - 1;
                int direction = Integer.parseInt(st.nextToken());

                // direction : 1 시계, -1 반시계
                int[] turnInfo = getTurnInfo(magnetIdx, direction);

                // 턴!!
                turn(turnInfo);
            }

            answer.append('#').append(t).append(' ').append(calcPoint()).append('\n');
        }

        System.out.println(answer);
    }

    static int calcPoint() {
        int point = 1;
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += (point * magnets[i][0]);
            point *= 2;
        }

        return result;
    }
    
    static void turn(int[] turnInfo) {
        for (int i = 0; i < 4; i++) {
            if (turnInfo[i] == 1) { // 시계 방향
                int last = magnets[i][7];
                for (int j = 7; j >= 1; j--) {
                    magnets[i][j] = magnets[i][j - 1];
                }
                magnets[i][0] = last;
            } else if (turnInfo[i] == -1) { // 반시계 방향
                int first = magnets[i][0];
                for (int j = 0; j < 7; j++) {
                    magnets[i][j] = magnets[i][j + 1];
                }
                magnets[i][7] = first;
            }
        }
    }

    static int[] getTurnInfo(int magnetIdx, int direction) {
        int[] turnInfo = new int[4];
        turnInfo[magnetIdx] = direction;

        // 돌린 자석 기준 왼쪽 자석들 판단
        int criteria = magnetIdx;
        int left = criteria - 1;
        int beforeDirection = direction;
        while (left >= 0) {
            if (magnets[criteria][6] != magnets[left][2]) {
                turnInfo[left] = -beforeDirection;
            } else {
                turnInfo[left] = 0;
                break;
            }

            criteria = left;
            left = criteria - 1;
            beforeDirection = -beforeDirection;
        }

        for (int i = left - 1; i >= 0; i--) {
            turnInfo[i]= 0;
        }

        criteria = magnetIdx;
        int right = criteria + 1;
        beforeDirection = direction;

        while (right < 4) {
            if (magnets[criteria][2] != magnets[right][6]) {
                turnInfo[right] = -beforeDirection;
            } else {
                turnInfo[right] = 0;
                break;
            }

            criteria = right;
            right = criteria + 1;
            beforeDirection = -beforeDirection;
        }

        for (int i = right + 1; i < 4; i++) {
            turnInfo[i]  =0;
        }

        return turnInfo;
    }
}
/*
i번 자석의 2번 vs i + 1 자석의 6번


 */