import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LCA {

    static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) {
        makeTree();

        int[] parent = new int[16];
        int[] depth = new int[16];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 1});
        parent[1] = 0;
        depth[1] = 1;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            int now = node[0];
            int d = node[1];

            for (int next : graph.get(now)) {
                parent[next] = now;
                depth[next] = d + 1;

                queue.add(new int[]{next, depth[next]});
            }
        }

        int target1 = 15;
        int target2 = 7;

        System.out.println(findLCA(target1, target2, parent, depth));
    }

    static int findLCA(int target1, int target2, int[] parent, int[] depth) {
        int depth1 = depth[target1];
        int depth2 = depth[target2];

        while (depth1 > depth2) {
            target1 = parent[target1];
            depth1 = depth[target1];
        }

        while (depth1 < depth2) {
            target2 = parent[target2];
            depth2 = depth[target2];
        }

        while (target1 != target2) {
            target1 = parent[target1];
            target2 = parent[target2];
        }

        return target1;
    }

    static void makeTree() {
        for (int i = 0; i <= 15; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(1).add(2);
        graph.get(1).add(3);

        graph.get(2).add(4);
        graph.get(2).add(5);

        graph.get(3).add(6);
        graph.get(3).add(7);

        graph.get(4).add(8);
        graph.get(4).add(9);

        graph.get(6).add(10);
        graph.get(6).add(11);

        graph.get(7).add(12);
        graph.get(7).add(13);

        graph.get(11).add(14);
        graph.get(11).add(15);
    }
}
