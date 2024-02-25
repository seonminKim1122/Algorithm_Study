package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _n2580_ {

    static int[][] board = new int[9][9];
    static List<Position> emptyPositions = new ArrayList<>();

    static boolean solved = false;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    emptyPositions.add(new Position(i, j));
                }
            }
        }

        // 스도쿠 채우기
        solve(0);

        System.out.println(answer);
    }

    static void solve(int idx){
        if (solved) return;

        if (idx >= emptyPositions.size()) {
            // 모든 빈 칸을 다 채웠으니 정답!
            solved = true;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    answer.append(board[i][j]).append(' ');
                }
                answer.append('\n');
            }
            return;
        }

        Position position = emptyPositions.get(idx);
        boolean[] isExist = new boolean[9];
        checkRow(isExist, position);
        checkColumn(isExist, position);
        checkSquare(isExist, position);

        for (int i = 0; i < 9; i++) {
            if (isExist[i]) continue;
            board[position.x][position.y] = (i + 1);
            solve(idx + 1);
            board[position.x][position.y] = 0;
        }
    }

    static void checkRow(boolean[] isExist, Position position) {
        int c = position.y;

        for (int r = 0; r < 9; r++) {
            if (board[r][c] != 0) {
                isExist[board[r][c] - 1] = true;
            }
        }
    }

    static void checkColumn(boolean[] isExist, Position position) {
        int r = position.x;
        for (int c = 0; c < 9; c++) {
            if (board[r][c] != 0) {
                isExist[board[r][c] - 1] = true;
            }
        }
    }

    static void checkSquare(boolean[] isExist, Position position) {
        int rs = (position.x / 3) * 3;
        int cs = (position.y / 3) * 3;

        for (int r = rs; r < rs + 3; r++) {
            for (int c = cs; c < cs + 3; c++) {
                if (board[r][c] != 0) {
                    isExist[board[r][c] - 1] = true;
                }
            }
        }
    }

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
