package dataStructure;

import java.util.Arrays;

public class Heap {

    int[] arr = new int[11];
    int size = 0;

    void add(int x) {
        arr[++size] = x;

        int node = size;

        while (node / 2 >= 1 && arr[node / 2] > arr[node]) {
            int temp = arr[node / 2];
            arr[node / 2] = arr[node];
            arr[node] = temp;
            node /= 2;
        }
    }

    int poll() {
        int root = arr[1];
        arr[1] = arr[size];
        arr[size--] = 0;

        int parent = 1;
        while (2 * parent <= size) {
            int left = arr[2 * parent];
            int right = arr[2 * parent + 1] == 0 ? Integer.MAX_VALUE : arr[2 * parent + 1];

            if (left < right) {
                if (left < arr[parent]) {
                    int temp = arr[parent];
                    arr[parent] = left;
                    arr[2 * parent] = temp;
                    parent = 2 * parent;
                } else {
                    break;
                }
            } else {
                if (right < arr[parent]) {
                    int temp = arr[parent];
                    arr[parent] = right;
                    arr[2 * parent + 1] = temp;
                    parent = 2 * parent + 1;
                } else {
                    break;
                }
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Heap heap = new Heap();

        heap.add(10);
        heap.add(9);
        heap.add(8);
        heap.add(7);
        heap.add(11);
        heap.add(12);
        heap.add(6);

        System.out.println(Arrays.toString(heap.arr));
        System.out.println(heap.poll());
        System.out.println(Arrays.toString(heap.arr));
        System.out.println(heap.poll());
        System.out.println(Arrays.toString(heap.arr));
        System.out.println(heap.poll());
        System.out.println(Arrays.toString(heap.arr));
        System.out.println(heap.poll());
        System.out.println(Arrays.toString(heap.arr));
        System.out.println(heap.poll());
        System.out.println(Arrays.toString(heap.arr));
        System.out.println(heap.poll());
        System.out.println(Arrays.toString(heap.arr));
        System.out.println(heap.poll());
    }
}
