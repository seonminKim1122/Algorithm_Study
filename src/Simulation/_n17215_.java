package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _n17215_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] pins = br.readLine().toCharArray();

        int totalScore = 0;
        int[] frames = new int[11];
        int frame = 1;
        int turn = 1;
        if (pins[0] == 'S') {
            for (int i = 0; i <= 2; i++) {
                frames[frame] += changeCharToInt(i, pins);
            }
            totalScore += frames[frame++];
        } else {
            frames[frame] += changeCharToInt(0, pins);
            turn++;
        }

        int idx = -1;
        for (int i = 1; i < pins.length; i++) {
            if (frame == 10) {
                idx = i;
                break;
            }

            if (pins[i] == 'S') {
                for (int j = i; j <= i + 2; j++) {
                    frames[frame] += changeCharToInt(j, pins);
                }
            } else if (pins[i] == 'P') {
                for (int j = i; j <= i + 1; j++) {
                    frames[frame] += changeCharToInt(j, pins);
                }
            } else {
                frames[frame] += changeCharToInt(i, pins);
            }

            if (pins[i] == 'S' || turn == 2) {
                totalScore += frames[frame++];
                turn = 1;
            } else {
                turn++;
            }
        }


        for (int i = idx; i < pins.length; i++) {
            totalScore += changeCharToInt(i, pins);
        }

        System.out.println(totalScore);
    }

    static int changeCharToInt(int idx, char[] pins) {
        if (idx < 0 || idx >= pins.length) return 0;

        char pin = pins[idx];
        if (pin == 'S') return 10;
        if (pin == 'P') return 10 - changeCharToInt(idx - 1, pins);
        if (pin == '-') return 0;
        return pin - '0';
    }
}