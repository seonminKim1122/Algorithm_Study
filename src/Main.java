import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        List<Integer> nodes = new ArrayList<>();
        while ((input = br.readLine()) != null && !input.equals("")) {
            nodes.add(Integer.parseInt(input));
        }

        // 배열 사이즈를 어떻게 줄 것인가에 대한 고민 필요
        int sizeOfTree = (int)Math.pow(2, nodes.size() - 1); 
        int[] tree = new int[sizeOfTree + 1];
        for (int node : nodes) {

            int idx = 1;
            while (tree[idx] != 0) {
                if (node < tree[idx]) {
                    if (2 * idx >= tree.length) break;
                    idx = 2 * idx;
                } else {
                    if (2 * idx + 1 >= tree.length) break;
                    idx = 2 * idx + 1;
                }
            }

            if (tree[idx] == 0) tree[idx] = node;
        }

        postOrder(tree, 1);
    }

    static void postOrder(int[] tree, int node) {
        if (node >= tree.length || tree[node] == 0) return;

        postOrder(tree, 2 * node);
        postOrder(tree, 2 * node + 1);
        System.out.println(tree[node]);
    }
}
/*
50
30
24
5
27
25
26
28
29
45
98
52
60
106
109
108
110
 */