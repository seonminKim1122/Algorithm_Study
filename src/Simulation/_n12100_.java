package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _n12100_ {

    static int N;
    static int[][] board;
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        solve(0);
        System.out.println(result);
    }

    static void solve(int depth) {
        if (depth == 5) {
            // 현재 상태의 보드에 있는 블록 중 가장 큰 블록 찾기
            int tempMax = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (tempMax < board[i][j]) {
                        tempMax = board[i][j];
                    }
                }
            }
            result = Math.max(result, tempMax);
            return;
        }
        
        // 상, 하, 좌, 우 네 방향 중 하나로 이동
        for (int i = 0; i < 4; i++) {
            int[][] before = beforeMove();
            // 보드 내의 블록 움직이기
            moveBlocks(i);
            solve(depth + 1);
            board = before;
        }
    }
    
    static int[][] beforeMove() {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = board[i][j];
            }
        }
        return result;
    }
    
    static void moveBlocks(int direction) {
        // 한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다
        // -> 이번에 합쳐진 블록은 다음 이동 때 다른 블록과 합쳐질 수 있다
        switch (direction) {
            case 0: // 상
                moveUp();
                break;
            case 1: // 하
                moveDown();
                break;
            case 2: // 좌
                moveLeft();
                break;
            default: // 우
                moveRight();
        }
    }
    // 움직이는 로직 재점검하기!!
    static void moveLeft() {
        int[][] cnt = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) cnt[i][j] = 1;
            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = 1; col < N; col++) {
                if (board[row][col] == 0) continue; // 0은 블록이 아니므로 pass
                for (int next = col - 1; next >= 0; next--) { // 블록인 경우 바로 위 자리부터 옮길 수 있는지 탐색
                    if (cnt[row][next] == 0) {
                        board[row][next] = board[row][next + 1];
                        board[row][next + 1] = 0;
                        cnt[row][next] = 1;
                        cnt[row][next + 1] = 0;
                    } else if (cnt[row][next] == 1) {
                        // 블록 합치기
                        if (board[row][next] == board[row][next + 1]) {
                            board[row][next] += board[row][next + 1];
                            board[row][next + 1] = 0;
                            cnt[row][next] = 2;
                            cnt[row][next + 1] = 0;
                        }
                        break;
                    } else { // 옮기려는 칸이 이미 블록이 합쳐진 곳이면 더 이상 합칠 수는 없으므로 다음 row 로 넘어가기
                        break;
                    }
                }
            }
        }
    }

    static void moveRight() {
        int[][] cnt = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) cnt[i][j] = 1;
            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = N - 2; col >= 0; col--) {
                if (board[row][col] == 0) continue; // 0은 블록이 아니므로 pass
                for (int next = col + 1; next < N; next++) { // 블록인 경우 바로 위 자리부터 옮길 수 있는지 탐색
                    if (cnt[row][next] == 0) {
                        board[row][next] = board[row][next - 1];
                        board[row][next - 1] = 0;
                        cnt[row][next] = 1;
                        cnt[row][next - 1] = 0;
                    } else if (cnt[row][next] == 1) {
                        // 블록 합치기
                        if (board[row][next] == board[row][next - 1]) {
                            board[row][next] += board[row][next - 1];
                            board[row][next - 1] = 0;
                            cnt[row][next] = 2;
                            cnt[row][next - 1] = 0;
                        }
                        break;
                    } else { // 옮기려는 칸이 이미 블록이 합쳐진 곳이면 더 이상 합칠 수는 없으므로 다음 row 로 넘어가기
                        break;
                    }
                }
            }
        }
    }

    static void moveDown() {
        int[][] cnt = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) cnt[i][j] = 1;
            }
        }

        for (int col = 0; col < N; col++) {
            for (int row = N - 2; row >= 0; row--) {
                if (board[row][col] == 0) continue; // 0은 블록이 아니므로 pass
                for (int next = row + 1; next < N; next++) { // 블록인 경우 바로 위 자리부터 옮길 수 있는지 탐색
                    if (cnt[next][col] == 0) {
                        board[next][col] = board[next - 1][col];
                        board[next - 1][col] = 0;
                        cnt[next][col] = 1;
                        cnt[next - 1][col] = 0;
                    } else if (cnt[next][col] == 1) {
                        // 블록 합치기
                        if (board[next][col] == board[next - 1][col]) {
                            board[next][col] += board[next - 1][col];
                            board[next - 1][col] = 0;
                            cnt[next][col] = 2;
                            cnt[next - 1][col] = 0;
                        }
                        break;
                    } else { // 옮기려는 칸이 이미 블록이 합쳐진 곳이면 더 이상 합칠 수는 없으므로 다음 row 로 넘어가기
                        break;
                    }
                }
            }
        }
    }

    static void moveUp() {
        int[][] cnt = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) cnt[i][j] = 1;
            }
        }

        for (int col = 0; col < N; col++) {
            for (int row = 1; row < N; row++) {
                if (board[row][col] == 0) continue; // 0은 블록이 아니므로 pass
                for (int next = row - 1; next >= 0; next--) { // 블록인 경우 바로 위 자리부터 옮길 수 있는지 탐색
                    if (cnt[next][col] == 0) {
                        board[next][col] = board[next + 1][col];
                        board[next + 1][col] = 0;
                        cnt[next][col] = 1;
                        cnt[next + 1][col] = 0;
                    } else if (cnt[next][col] == 1) {
                        // 블록 합치기
                        if (board[next][col] == board[next + 1][col]) {
                            board[next][col] += board[next + 1][col];
                            board[next + 1][col] = 0;
                            cnt[next][col] = 2;
                            cnt[next + 1][col] = 0;
                        }
                        break;
                    } else { // 옮기려는 칸이 이미 블록이 합쳐진 곳이면 더 이상 합칠 수는 없으므로 다음 row 로 넘어가기
                        break;
                    }
                }
            }
        }
    }
}
/*
1초, N 최대 20
4 x 4 x 4 x 4 x 4 = 1024 * 400 -> 1초 가능

7
2 2 2 2 2 2 2
2 0 2 2 2 2 2
2 0 2 2 2 2 2
2 0 2 2 2 2 2
2 2 2 0 2 2 2
2 2 2 2 2 2 0
2 2 2 2 2 2 0
 */