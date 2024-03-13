package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _n1446_ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[] distance = new int[D+1];
        for (int i = 1; i <= D; i++) {
            distance[i] = i;
        }

        PriorityQueue<Shortcut> shortcuts = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            Shortcut shortcut = new Shortcut(s, e, d);
            shortcuts.add(shortcut);
        }

        while (!shortcuts.isEmpty()) {
            Shortcut shortcut = shortcuts.poll();

            int s = shortcut.start;
            int e = shortcut.end;
            int d = shortcut.dist;

            if (e > D) continue;

            if (distance[e] > distance[s] + d) {
                distance[e] = distance[s] + d;
                for (int i = e + 1; i <= D; i++) {
                    distance[i] = Math.min(distance[i], distance[e] + i - e);
                }
            }
        }

        System.out.println(distance[D]);
    }

    static class Shortcut implements Comparable<Shortcut> {
        int start;
        int end;
        int dist;

        Shortcut(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Shortcut another) {
            return this.end - another.end;
        }
    }
}
/*

 */