package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class _n1302_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        HashMap<String, Integer> sails = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String key = br.readLine();
            sails.put(key, sails.getOrDefault(key, 0) + 1);
        }

        int max = 0;
        String book = "";

        for (String key : sails.keySet()) {
            if (sails.get(key) > max) {
                max = sails.get(key);
                book = key;
            } else if(sails.get(key) == max) {
                book = (key.compareTo(book) < 0) ? key : book;
            }
        }

        System.out.println(book);
    }
}
/*
최악의 경우 팔린 책이 모두 다를 수 있으므로
시간 복잡도 O(N)
 */