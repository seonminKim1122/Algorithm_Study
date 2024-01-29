package DataStructure;

public class Deque {

    int front = 0;
    int rear = 0;

    int[] arr = new int[10];

    void addFirst(int v) {
        arr[front--] = v;
        if (front < 0) front = 9;
    }

    void addLast(int v) {
        arr[++rear] = v;
        if (rear >= 10) rear = 0;
    }

    int pollFirst() {
        front = (front + 1) % 10;
        int v = arr[front];
        arr[front] = 0;
        return v;
    }

    int pollLast() {
        int v = arr[rear];
        arr[rear--] = 0;
        if (rear < 0) rear = 9;
        return v;
    }

    public static void main(String[] args) {
        Deque deque = new Deque();

        deque.addFirst(3);
        deque.addLast(5);
        deque.addFirst(1);
        deque.addLast(4);
        deque.addFirst(10);
        // {10, 1, 3, 5, 4}
        System.out.println(deque.pollFirst()); // 10
        System.out.println(deque.pollLast()); // 4
        System.out.println(deque.pollFirst()); // 1
        System.out.println(deque.pollLast()); // 5
    }
}
